package game;

import game.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    LineRepository lineRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUsed_Use(@Payload Sended used){

        if(used.isMe()){
            System.out.println("##### listener Use : " + used.toJson());

            Line line = new Line();
            line.setGiftId(used.getId());
            line.setStatus(used.getStatus());

            lineRepository.save(line);
        }
    }

}
