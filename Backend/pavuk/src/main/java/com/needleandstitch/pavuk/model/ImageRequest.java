package com.needleandstitch.pavuk.model;


public class ImageRequest {
	private String name;
    private String svgData;
	
	
	public ImageRequest() {}
	
	public ImageRequest(String name, String svgData) {
        this.name = name;
        this.svgData = svgData;
    }
	
	
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSvgData() {
        return svgData;
    }

    public void setSvgData(String svgData) {
        this.svgData = svgData;
    }
}
