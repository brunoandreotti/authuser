package com.brunoandreotti.authuser.clients;

import com.brunoandreotti.authuser.dtos.CourseRecordDTO;
import com.brunoandreotti.authuser.dtos.ResponsePageDTO;
import com.brunoandreotti.authuser.exceptions.ClientErrorException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

@Component
@Log4j2
public class CourseClient {

    @Value("${integration.url.course}")
    private String courseBaseUrl;

    private final RestClient restClient;

    public CourseClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public Page<CourseRecordDTO> getAllCoursesByUser(UUID userId, Pageable pageable) {

        String url = courseBaseUrl + String.format("/courses?userId=%s&page=%s&size=%s&sort=%s",

                userId,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort().
                        toString().
                        replaceAll(":",",").
                        replaceAll(" ", ""));

        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ResponsePageDTO<CourseRecordDTO>>() {});
        } catch (RestClientException ex) {
           log.error("Error Request RestClient with cause: {}", ex.getMessage());
           throw new RuntimeException("Error Request RestClient", ex);
        }
    }

    public void deleteCourseUserInCourse(UUID userId) {
        String url = courseBaseUrl + String.format("/courses/users/%s", userId);

        log.info("DELETE - deleteCourseUserInCourse");
        restClient.delete()
                .uri(url)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        (request, response) -> {
                            log.error("Error Request deleteCourseUserInCourse with cause: {}", response.getBody().toString());
                            throw new ClientErrorException("Error Request RestClient deleteCourseUserInCourse", response.getStatusCode().value());
                        })
                .toBodilessEntity();
    }
}
