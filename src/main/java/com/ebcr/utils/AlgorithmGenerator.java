package com.ebcr.utils;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmGenerator {
    private Algorithm algorithm = Algorithm.HMAC256("jabesnshuti90847%%%".getBytes());

}
