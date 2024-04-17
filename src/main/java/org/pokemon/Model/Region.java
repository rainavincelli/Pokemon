package org.pokemon.Model;

public class Region {

    private int regionId;
    private String regionName;
    private String mapUrl;

    public Region() {
    }

    public Region(int regionId, String regionName, String mapUrl) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.mapUrl = mapUrl;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    @Override
    public String toString() {
        return "Region Name: " + regionName + System.lineSeparator() +
                "Map Link: " + mapUrl;
    }
}
