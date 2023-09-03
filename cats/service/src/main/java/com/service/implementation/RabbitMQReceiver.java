package com.service.implementation;

import com.dto.CatDTO;
import com.mappers.ICatMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ComponentScan("com.mappers")
public class RabbitMQReceiver implements RabbitListenerConfigurer {
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @Autowired
    private CatsService catsService;
    @Autowired
    private ICatMapper catMapper;

    @RabbitListener(queues = "cat.queue.add")
    @Transactional
    public void saveCat(CatDTO catDTO) {
        catsService.saveCat(catMapper.unDTO(catDTO));
    }

    @RabbitListener(queues = "cat.queue.delete")
    @Transactional
    public void deleteCat(CatDTO catDTO) {
        catsService.deleteCat(catMapper.unDTO(catDTO));
    }

    @RabbitListener(queues = "cat.queue.deleteById")
    @Transactional
    public void deleteCatById(Integer id) {
        catsService.deleteCatById(id);
    }

    @RabbitListener(queues = "cat.queue.deleteAll")
    @Transactional
    public void deleteCats() {
        catsService.deleteCats();
    }

    @RabbitListener(queues = "cat.queue.getById")
    @Transactional
    public CatDTO getByIdCat(Integer id) {
        return catMapper.toDTO(catsService.findByIdCat(id));
    }

    @RabbitListener(queues = "cat.queue.getAll")
    @Transactional
    public List<CatDTO> getCats() {
        return catMapper.toDTOs(catsService.getAllCats());
    }

    @RabbitListener(queues = "cat.queue.getOwnerCats")
    @Transactional
    public List<CatDTO> getOwnerCats(Integer id) {
        return catMapper.toDTOs(catsService.getOwnerCats(id));
    }

    @RabbitListener(queues = "cat.queue.getFriendsCat")
    @Transactional
    public List<CatDTO> getFriendsCat(Integer id) {
        return catMapper.toDTOs(catsService.getFriendsCat(id));
    }
}