package cn.jvtc.controller;

import cn.jvtc.po.Goods;
import cn.jvtc.po.User;
import cn.jvtc.service.GoodsService;
import cn.jvtc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Goods controller.
 *
 * @author 雷族
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * Find by con page r.
     *
     * @param pageCode the page code
     * @param pageSize the page size
     * @param title    the title
     * @param brand    the brand
     * @param session  the session
     * @return the r
     */
    @RequestMapping("/findByConPage.action")
    public @ResponseBody
    R findByConPage(
            @RequestParam(value = "pageCode", required = false) int pageCode,
            @RequestParam(value = "pageSize", required = false) int pageSize,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "brand", required = false) String brand, HttpSession session) {
        R r = new R();
        try {
            //获取session会话保存的登录用户
            User userSession = (User) session.getAttribute("user");
            r.setFlag(userSession != null);
            if (userSession != null) {
                //通过条件用户id查询用户商品数据
                //此处赋值只为查询当数据，不做数据更改
                Goods goods = new Goods();
                goods.setUserId(userSession.getId());
                //标题和品牌名条件判断
                if (!"undefined".equals(title)) {
                    goods.setTitle(title);
                }
                if (!"undefined".equals(brand)) {
                    goods.setBrand(brand);
                }
                r.setData(goodsService.findByPage(goods, pageCode, pageSize));
                r.setMsg("查询成功！");
            } else {
                r.setMsg("数据异常，该用户失效，请检查session");
            }

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return r;
    }


    /**
     * Update r.
     *
     * @param goods   the goods
     * @param session the session
     * @return the r
     */
    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    public @ResponseBody
    R update(@RequestBody Goods goods, HttpSession session) {
        R r = new R();
        try {
            int update = goodsService.update(goods);
            r.setData(update);
            r.setFlag(update > 0);
            r.setMsg(r.getFlag() ? "修改" + update + "记录成功" : "修改失败");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }


    /**
     * Delete r.
     *
     * @param id the id
     * @return the r
     */
    @DeleteMapping("/delete.action")
    public @ResponseBody
    R delete(String id) {
        R r = new R();
        try {
            goodsService.delete(Integer.parseInt(id));
            r.setFlag(true);
            r.setMsg("删除记录成功！");

        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Create r.
     *
     * @param goods   the goods
     * @param session the session
     * @return the r
     */
    @ResponseBody
    @RequestMapping("/create.action")
    public R create(@RequestBody Goods goods, HttpSession session) {
        R r = new R();
        try {
            User userSession = (User) session.getAttribute("user");
            if (userSession != null) {
                //商品与用户相关联
                goods.setUserId(userSession.getId());
            }
            r.setFlag(goodsService.add(goods) > 0);
            r.setMsg(r.getFlag() ? "添加成功！" : "添加失败！");
        } catch (Exception e) {
            r.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return r;
    }

    /**
     * Upload r.
     *
     * @param picture the picture
     * @param request the request
     * @return the r
     */
    @RequestMapping("/upload.action")
    public @ResponseBody
    R upload(@RequestParam("picture") MultipartFile picture, HttpServletRequest request) {
        //获取文件在服务器的储存位置
//        String path = request.getSession().getServletContext().getRealPath("/upload");
        String path = "D:\\upload\\SSM_CRUD";
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + "_" + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return new R(true, "/upload/SSM_CRUD/" + fileName);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new R(false, "上传失败" + e.getMessage());
        }
    }
}
