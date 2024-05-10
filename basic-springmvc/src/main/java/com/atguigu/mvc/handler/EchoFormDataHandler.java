package com.atguigu.mvc.handler;

import com.atguigu.mvc.pojo.Paige;
import com.atguigu.mvc.pojo.Season;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @PACKAGE_NAME: com.atguigu.mvc.handler
 * @CLASSNAME: EchoFormDataHandler
 * @AUTHOR: zhangsan
 * @DATE: 2024/1/26 11:51
 * @SINCE 17.0.7
 * @DESCRIPTION: TODO
 */
@Controller
@RequestMapping("/echo")
public class EchoFormDataHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/radio")
    public String echoRadio(Model model) {
        //1.准备用来显示标签的数据
        ArrayList<Season> seasons = new ArrayList<>();
        Season spring = new Season("spring", "春天");
        Season summer = new Season("summer", "夏天");
        Season autumn = new Season("autumn", "秋天");
        Season winter = new Season("winter", "冬天");
        Collections.addAll(seasons, spring, summer, autumn, winter);
        model.addAttribute("seasonList", seasons);

        //2.准备用来回显表单的数据
        Paige paige = new Paige(1001, "小猪佩奇", spring, null);
        model.addAttribute("paige", paige);
        return "update";
    }

    @RequestMapping("/checkbox")
    public String echoCheckbox(Model model) {
        //1.准备用来显示标签的数据
        List<Season> seasons = new ArrayList<>(4);
        Season spring = new Season("spring", "春天");
        Season summer = new Season("summer", "夏天");
        Season autumn = new Season("autumn", "秋天");
        Season winter = new Season("winter", "冬天");
        Collections.addAll(seasons, spring, summer, autumn, winter);
        model.addAttribute("seasonList", seasons);

        //2.准备用来回显表单的数据
        Paige paige = new Paige();
        paige.setPaigeId(1001);
        paige.setPaigeName("小刘佩奇");
        paige.setSeason(summer);
        List<Season> paigeSeasonList = new ArrayList<>(3);
        Collections.addAll(paigeSeasonList, spring, summer, winter);
        paige.setSeasonList(paigeSeasonList);
        model.addAttribute("paige", paige);
        return "checkbox";
    }
}
