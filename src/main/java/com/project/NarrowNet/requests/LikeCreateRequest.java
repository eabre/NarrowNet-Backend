package com.project.NarrowNet.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {

    Long id;
    Long postId;
    Long userId;

}
