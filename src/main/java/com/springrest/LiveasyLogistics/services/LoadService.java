package com.springrest.LiveasyLogistics.services;

import com.springrest.LiveasyLogistics.entities.Loads;

import java.util.List;

public interface LoadService {
    public List<Loads> getLoads();
    public Loads getLoad(String loadId);

   public Loads addLoad(Loads load);

   public Loads updateLoad(String loadId , Loads load);

   public void deleteLoad(String loadId);

    public List<Loads> getLoadByShipperId(String shipperId);
}
