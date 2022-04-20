package com.face.utils;

public class DistanceUtils {
  private static double rad(double d) {
    return d * Math.PI / 180.0;
  }

  public static double isInCircle(double lon1,double lat1,double lon2,double lat2){
    final double EARTH_RADIUS = 6378.137;
    double radLat1 = rad(lat1);
    double radLat2 = rad(lat2);
    double radLon1 = rad(lon1);
    double radLon2 = rad(lon2);
    double jdDistance = radLat1 - radLat2;
    double wdDistance = radLon1 - radLon2;
    double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(jdDistance / 2), 2) +
        Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(wdDistance / 2), 2)));
    distance = distance * EARTH_RADIUS;
    distance = Math.round(distance * 10000d) / 10000d;
    distance = distance*1000;//将计算出来的距离千米转为米
    System.out.print("1:");
    System.out.println(distance);

    return distance;
  }

//  public static boolean isInCircle(double lon1,double lat1,double lon2,double lat2,String radius){
//    final double EARTH_RADIUS = 6378.137;
//    double radLat1 = rad(lat1);
//    double radLat2 = rad(lat2);
//    double radLon1 = rad(lon1);
//    double radLon2 = rad(lon2);
//    double jdDistance = radLat1 - radLat2;
//    double wdDistance = radLon1 - radLon2;
//    double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(jdDistance / 2), 2) +
//        Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(wdDistance / 2), 2)));
//    distance = distance * EARTH_RADIUS;
//    distance = Math.round(distance * 10000d) / 10000d;
//    distance = distance*1000;//将计算出来的距离千米转为米
//    System.out.print("1:");
//    System.out.println(distance);
//    double r = Double.parseDouble(radius);
//    //判断一个点是否在圆形区域内
//    if (distance > r) {
//      return false;
//    }
//    return true;
//  }
}