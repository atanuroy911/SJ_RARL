package com.rarl.sj.controller;

import com.alibaba.fastjson.JSON;
import com.rarl.sj.entity.DataEntity;
import com.rarl.sj.entity.FoodEntity;
import com.rarl.sj.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


@Controller
public class FoodController implements ErrorController {
    @Autowired
    FoodRepository foodRepository;

    @RequestMapping("/json_data")
    @ResponseBody
    public List<FoodEntity> list() {
        List<FoodEntity> foodList = foodRepository.findAll();
        for (FoodEntity foodEntity : foodList) {
            System.out.println(foodEntity.toString());
        }

        return foodList;
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void recv_data(@RequestBody String json){

        List<DataEntity> staff1 = JSON.parseArray(json, DataEntity.class);
        //System.out.println(staff1.get(5).getName());
        System.out.println(staff1.size());
        for(int i = 0; i< staff1.size(); i++) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://120.79.140.98:3306/SJ", "root", "991211");
                Statement stmt = con.createStatement();
                String sql;
                if (staff1.get(i).getEval().equals("like")) {
                    sql = "UPDATE food SET Evaluation_1 = Evaluation_1 + 1 where name = '" + staff1.get(i).getName() + "'";
                    stmt.execute(sql);
                } else if (staff1.get(i).getEval().equals("dislike")) {
                    sql = "UPDATE food SET Evaluation_2 = Evaluation_2 + 1 where name = '" + staff1.get(i).getName() + "'";
                    stmt.execute(sql);
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }


    @RequestMapping("/index")
    public ModelAndView toIndex() {

        return new ModelAndView("index");
    }

    @RequestMapping(value="/event-count", method=RequestMethod.GET)
    public String getEventCount(ModelMap map) {
        // TODO: retrieve the new value here so you can add it to model map
        String value = "0";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://120.79.140.98:3306/SJ", "root", "991211");
            Statement stmt = con.createStatement();
            ResultSet rs;
            String sql = "SELECT SUM(Evaluation_1 + Evaluation_2) from food";
            stmt.executeQuery(sql);
            rs = stmt.getResultSet();
            while (rs.next()) {
                value = rs.getString("SUM(Evaluation_1 + Evaluation_2)");
//                System.out.println(value);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        map.addAttribute("att", value);

        // change "myview" to the name of your view
        return "index :: #eventCount";
    }

    @RequestMapping(value="/clear", method=RequestMethod.GET)
    public ModelAndView clearAll() {
        // TODO: retrieve the new value here so you can add it to model map
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://120.79.140.98:3306/SJ", "root", "991211");
            Statement stmt = con.createStatement();
            String sql = "UPDATE food SET Evaluation_1 = 0, Evaluation_2 = 0";
            stmt.executeUpdate(sql);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        // change "myview" to the name of your view
        return new ModelAndView("redirect:index");
    }

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }



}
