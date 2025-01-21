/*package com.example.rentacar.business.concretes;

import com.example.rentacar.models.Rent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationManager {
    private final Logger logger = LoggerFactory.getLogger(NotificationManager.class);
    @KafkaListener(topics = "rent-notification",
                    groupId = "group-id1")
    private void consume(ConsumerRecord<String, Rent> rent){
        logger.info(String.valueOf(rent.value()));
    }
}
*/
