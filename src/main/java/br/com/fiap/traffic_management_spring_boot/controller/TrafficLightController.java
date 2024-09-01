package br.com.fiap.traffic_management_spring_boot.controller;

import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightCreateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightUpdateDto;
import br.com.fiap.traffic_management_spring_boot.dto.traffic_light.TrafficLightViewDto;
import br.com.fiap.traffic_management_spring_boot.service.TrafficLightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TrafficLightController {

    @Autowired
    TrafficLightService trafficLightService;

    @PostMapping("/traffic-light")
    @ResponseStatus(HttpStatus.CREATED)
    public TrafficLightViewDto insertOne(@RequestBody @Valid TrafficLightCreateDto trafficLightDto){
        return this.trafficLightService.insertOne(trafficLightDto);
    }

    @GetMapping("/traffic-light")
    @ResponseStatus(HttpStatus.OK)
    public Page<TrafficLightViewDto> findAll(Pageable pagination){
        return this.trafficLightService.findAll(pagination);
    }

    @GetMapping("/traffic-light/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TrafficLightViewDto> findOne(@PathVariable Long id){
        return this.trafficLightService.findOneById(id);
    }

    @GetMapping("/traffic-light/{lat}/{lng}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TrafficLightViewDto> findManyByProximity(
            @PathVariable Double lat,
            @PathVariable Double lng,
            Pageable pagination
    ){
        return this.trafficLightService.findManyByProximity(lat, lng, pagination);
    }

    @GetMapping("/traffic-light/created-at/{init}/{end}")
    @ResponseStatus(HttpStatus.OK)
    public Page<TrafficLightViewDto> findManyByCreatedAtPeriod(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date init,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end,
            Pageable pagination
    ){
        return this.trafficLightService.findManyByCreatedAtPeriod(init, end, pagination);
    }

    @PutMapping("/traffic-light")
    @ResponseStatus(HttpStatus.OK)
    public TrafficLightViewDto updateOne(@RequestBody TrafficLightUpdateDto trafficLightDto){
        return this.trafficLightService.updateOne(trafficLightDto);
    }

    @DeleteMapping("/traffic-light/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable Long id){
        this.trafficLightService.deleteOneById(id);
    }

}
