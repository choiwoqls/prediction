package com.prediction.prediction.service.serviceImpl.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.repository.game.GameRepository;
import com.prediction.prediction.service.service.game.GameService;
import com.prediction.prediction.service.service.game.Game_PlayService;
import com.prediction.prediction.service.service.player.TeamService;
import com.prediction.prediction.service.service.user.MessageService;
import com.prediction.prediction.service.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final Game_PlayService gamePlayService;

    @Autowired
    private final MessageService messageService;

    @Autowired
    private final UserService userService;

    @Override
    public GameDTO saveGame(GameDTO gameDto) {
        try{
            Integer home_id = gameDto.getHome_id();
            Integer away_id = gameDto.getAway_id();
            if(!teamService.checkTeam(home_id)){
                throw new NotFoundException("잘못된 Home_id 입력");
            }
            if(!teamService.checkTeam(away_id)){
                throw new NotFoundException("잘못된 Away_id 입력");
            }
            if(home_id.equals(away_id)) {
                throw new BadRequestAlertException("잘못된 팀 선택.");
            }
            Game game = new Game();

            game.setHome(teamService.getTeamById(home_id));
            game.setAway(teamService.getTeamById(away_id));

            //todo domain 에서 default 0 설정.
            game.setResult(0);
            game.setStatus(0);
            game.setDate(gameDto.getDate());

            gameDto = new GameDTO(gameRepository.save(game));
            return gameDto;
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public MessageDto resultGame(int result, Long game_id) {
        MessageDto message = new MessageDto();
        message.setType("message");
        try{
            if(result == 4){
                //경기 취소일 경우
                //todo 경기 취소 시 코드 추가.
                gamePlayService.deleteExpect(game_id);
                message.setData("경기 취소.");
                return message;
            }
            Game game = gameRepository.findById(game_id).orElse(null);
            if(game == null){
                throw new NotFoundException("찾을 수 없는 경기 : 2");
            }
            if(gameRepository.resultGame(result, game_id) == 0){
                throw new NotFoundException("경기 결과 반영 실패 : 1");
            }
            //경기 결과 팀 성적에 반영
            teamService.updateResult(result, game);
            //회원 별 경기 예측 결과 반영
            gamePlayService.updateExpect(result, game_id);
            //예측 참여한 USER LIST -> 알림 보내기
            List<User> user_list = gamePlayService.userList(game_id);
            messageService.sendMessage(user_list, 1,"승부예측 결과를 확인해 주세요.");
            //예측 성공한 USER LIST -> +10 Credit
            List<User> success_list = gamePlayService.successList(game_id);
            userService.addCredit(success_list);

            message.setData("경기 결과 반영 성공");

        }catch (RuntimeException e){
            throw new BadRequestAlertException("게임 결과 반영 실패");
        }catch(Exception e){
            throw new CustomException(e);
        }
        return message;
    }

}
