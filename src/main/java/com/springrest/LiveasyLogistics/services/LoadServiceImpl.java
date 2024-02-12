package com.springrest.LiveasyLogistics.services;

import com.springrest.LiveasyLogistics.Dao.LoadDao;
import com.springrest.LiveasyLogistics.entities.Loads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Service
public class LoadServiceImpl implements LoadService{

    @Autowired
   private LoadDao loadDao;

    public LoadServiceImpl() {

    }

    @Override
    public List<Loads> getLoads() {
           return  loadDao.findAll();
    }

    @Override
    public Loads getLoad(String loadId){
        Optional<Loads> result = loadDao.findById(loadId);
        return result.orElse(null);
    }

    @Override
    public Loads addLoad(Loads load) {
        load.setLoadId(UUID.randomUUID().toString().split("-")[0]);
        loadDao.save(load);
        return load;
    }

    @Override
    public Loads updateLoad(String loadId , Loads load) {
        load.setLoadId(loadId);
        loadDao.save(load);
        return load;
    }

    @Override
    public void deleteLoad(String loadId) {
        loadDao.deleteById(loadId);
    }

    @Override
    public List<Loads> getLoadByShipperId(String shipperId) {
        List<Loads> allLoads = loadDao.findAll();
        List<Loads> shipperLoads = new ArrayList<>();

        for(Loads load : allLoads){
            if(load.getShipperId().toString().equals(shipperId)) shipperLoads.add(load);
        }
        return shipperLoads;
    }
}
