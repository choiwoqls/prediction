package com.prediction.prediction.dto.request.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Community_LikeDTO {
    private Long id;
    private Long community_id;
    private Long user_id;
}
