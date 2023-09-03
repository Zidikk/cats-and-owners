package com.service.implementation;

import com.dto.OwnerDTO;
import com.mappers.IOwnerMapper;
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
    private OwnersService ownersService;
    @Autowired
    private IOwnerMapper ownerMapper;

    @RabbitListener(queues = "owner.queue.add")
    @Transactional
    public void saveOwner(OwnerDTO ownerDTO) {
        ownersService.saveOwner(ownerMapper.unDTO(ownerDTO));
    }

    @RabbitListener(queues = "owner.queue.delete")
    @Transactional
    public void deleteOwner(OwnerDTO ownerDTO) {
        ownersService.deleteOwner(ownerMapper.unDTO(ownerDTO));
    }

    @RabbitListener(queues = "owner.queue.deleteById")
    @Transactional
    public void deleteOwnerById(Integer id) {
        ownersService.deleteOwnerById(id);
    }

    @RabbitListener(queues = "owner.queue.deleteAll")
    @Transactional
    public void deleteOwners() {
        ownersService.deleteOwners();
    }

    @RabbitListener(queues = "owner.queue.getById")
    @Transactional
    public OwnerDTO getByIdOwner(Integer id) {
        return ownerMapper.toDTO(ownersService.findByIdOwner(id));
    }

    @RabbitListener(queues = "owner.queue.getAll")
    @Transactional
    public List<OwnerDTO> getOwners() {
        return ownerMapper.toDTOs(ownersService.getAllOwners());
    }
}