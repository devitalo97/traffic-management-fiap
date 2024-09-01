package br.com.fiap.traffic_management_spring_boot.service;

import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightUpdateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightViewDto;
import br.com.fiap.traffic_management_spring_boot.exception.NotFoundException;
import br.com.fiap.traffic_management_spring_boot.model.TrafficLight;
import br.com.fiap.traffic_management_spring_boot.repository.TrafficLightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TrafficLightService {

    @Autowired
    TrafficLightRepository trafficLightRepository;

    public TrafficLightViewDto insertOne(TrafficLightCreateDto trafficLightDto){
        TrafficLight trafficLight = new TrafficLight();
        BeanUtils.copyProperties(trafficLightDto, trafficLight);
        trafficLight.setCreatedAt(new Date());
        TrafficLight entity = this.trafficLightRepository.save(trafficLight);
        return new TrafficLightViewDto(entity);
    }

    public Optional<TrafficLightViewDto> findOneById(Long id){
        Optional<TrafficLight> trafficLight = this.trafficLightRepository.findById(id);
        return trafficLight.map(TrafficLightViewDto::new);
    }

    public Page<TrafficLightViewDto> findAll(Pageable pagination){
        Page<TrafficLight> trafficLightList = this.trafficLightRepository.findAll(pagination);
        return trafficLightList.map(TrafficLightViewDto::new);
    }

    public Page<TrafficLightViewDto> findManyByProximity(Double lat, Double lng, Pageable pagination){
        Page<TrafficLight> trafficLightList = this.trafficLightRepository.findManyByProximity(lat, lng, pagination);
        return trafficLightList.map(TrafficLightViewDto::new);
    }

    public Page<TrafficLightViewDto> findManyByCreatedAtPeriod(Date init, Date end, Pageable pagination){
        Page<TrafficLight> trafficLightList = this.trafficLightRepository.findManyByCreatedAtPeriod(init, end, pagination);
        return trafficLightList.map(TrafficLightViewDto::new);
    }

    public void deleteOneById(Long id){
        Optional<TrafficLight> trafficLightOptional = this.trafficLightRepository.findById(id);
        if(trafficLightOptional.isPresent()){
            this.trafficLightRepository.deleteById(id);
        }else{
            throw new NotFoundException("Traffic Light doesn't exists");
        }
    }

    public TrafficLightViewDto updateOne(TrafficLightUpdateDto trafficLightDto){
        Optional<TrafficLight> trafficLightOptional = this.trafficLightRepository.findById(trafficLightDto.id());
        if(trafficLightOptional.isPresent()){
            TrafficLight trafficLight = trafficLightOptional.get();
            BeanUtils.copyProperties(trafficLightDto, trafficLight);
            trafficLight.setUpdatedAt(new Date());
            TrafficLight entity = this.trafficLightRepository.save(trafficLight);
            return new TrafficLightViewDto(entity);
        }else{
            throw new NotFoundException("Traffic Light doesn't exists");
        }
    }
}
