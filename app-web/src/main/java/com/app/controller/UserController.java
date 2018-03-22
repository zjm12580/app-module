package com.app.controller;

import com.app.entity.PageDataTable;
import com.app.entity.User;
import com.app.entity.Visitor;
import com.app.iService.IUserService;
import com.app.utils.JsonUtils;
import com.app.utils.ResultMap;
import com.app.utils.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * com.app
 *
 * @author zhujiamin
 * @date 2017/7/26
 */

@Controller
public class UserController extends BaseController {

    @Resource
    private IUserService iUserService;

    /**
     * 获取用户
     *
     * @return
     */
    @RequestMapping("/user/getUsers")
    @ResponseBody
    public List<User> getAllUsers() {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("")
        return iUserService.getAllUsers();

    }


    /**
     * 获取用户
     *
     * @return
     */
    @RequestMapping("/user/changePasswordIndex")
    public String changePasswordIndex(Integer id, Model model) {
        User param = new User();
        param.setId(id);
        List<User> userList = iUserService.getUsers(param);
        if (userList != null && userList.size() > 0) {
            model.addAttribute("user", userList.get(0));
        }
        return "/ChangePassword";

    }

    /**
     * 游客模块
     *
     * @return
     */
    @RequestMapping("/user/visitor")
    public String visitor() {
        return "/Visitor";

    }

    @RequestMapping("/user/VisitorManager")
    public String VisitorManager() {
        return "/VisitorManager";
    }

    /**
     * 员工管理
     *
     * @return
     */
    @RequestMapping("/user/employeeIndex")
    public String employeemIndex() {
        return "/employeeIndex";

    }

    @RequestMapping("/user/addUserIndex")
    public String addUserIndex() {
        return "/AddUserIndex";

    }

    /**
     * 更新用户页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/user/updateUserIndex")
    public String updateUserIndex(Long id, Model model) {
        User param = new User();
        param.setId(id);
        List<User> userList = iUserService.getUsers(param);
        if (userList != null && userList.size() > 0) {
            model.addAttribute("user", userList.get(0));
        }
        return "/updateUserIndex";
    }

    /**
     * 权限管理
     *
     * @return
     */
    @RequestMapping("/user/adminRoleIndex")
    public String adminRoleIndex() {
        return "/adminRole";
    }


    /**
     * 获取用户
     *
     * @param userparam
     * @return
     */
    @RequestMapping("/user/getUser")
    @ResponseBody
    public PageDataTable getUser(User userparam) {
        PageDataTable<User> res = new PageDataTable();

        try {
            if (userparam == null) {
                throw new RuntimeException("参数为空");
            }
            List<User> userList = iUserService.getUsers(userparam);
            Integer count = iUserService.countUser(userparam);
            res.setAaData(userList);
            res.setsEcho(0);
            res.setiTotalDisplayRecords(count);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return res;
    }

    /**
     * 停用账号
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/stopUser")
    @ResponseBody
    public Map stopUser(Long id, Integer status) {
        try {
            User userParam = new User();
            userParam.setId(id);
            userParam.setStatus(status);
            iUserService.updateUser(userParam);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/deleteUser")
    @ResponseBody
    public Map deleteUser(Long id) {
        try {
            User userParam = new User();
            userParam.setId(id);
            iUserService.deleteUser(userParam);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }


    /**
     * 更新密码
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping("/user/modifyPassword")
    @ResponseBody
    public Map modifyPassword(Long id, String password) {
        try {
            User userParam = new User();
            userParam.setId(id);
            userParam.setPassword(password);
            iUserService.deleteUser(userParam);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/updateUser")
    @ResponseBody
    public Map updateUser(User user) {
        try {
            if (user == null) {
                throw new RuntimeException("传入的参数为空");
            }
            iUserService.updateUser(user);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }

    @RequestMapping("/user/batchDelete")
    @ResponseBody
    public Map batchDelete(@RequestBody() String ids) {
        List<Integer> idList = JsonUtils.parseObject(ids, List.class);
        if (idList == null || idList.size() == 0) {
            return ResultMap.failed("所选数据为空");
        }
        try {
            if (iUserService.batchDelete(idList) > 0) {
                return ResultMap.success();
            } else {
                return ResultMap.failed("删除失败");
            }
        } catch (Exception e) {
            return ResultMap.failed("删除异常，请重试");
        }
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/changePassword")
    @ResponseBody
    public Map changePassword(User user) {
        try {
            if (user == null) {
                throw new RuntimeException("传入的参数为空");
            }
            iUserService.updateUser(user);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/addUser")
    @ResponseBody
    public Map addUser(User user) {
        try {
            if (user == null) {
                throw new RuntimeException("传入的参数为空");
            }
            user.setStatus(0);
            user.setMtime(new Date());
            user.setCtime(new Date());
            iUserService.insertUser(user);
        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("处理成功");
    }


    /**
     * 留言
     * * @return
     */
    @RequestMapping("/user/addMsg")
    @ResponseBody
    public Map addMsg(Visitor visitor) {
        try {
            if (visitor == null) {
                throw new RuntimeException("传入的参数为空");
            }
            visitor.setCtime(new Date());
            visitor.setId(Visit.i);
            visitor.setUrl("localhost:8080/app/visitor");
            visitor.setName("游客" + Visit.i);
            Visit.visitorMap.put(Visit.i, visitor);
            Visit.i += 1;
            System.out.println(Visit.visitorMap.size());

        } catch (Exception e) {
            return ResultMap.failed(e.getMessage());
        }
        return ResultMap.success("留言成功");
    }


    /**
     * 留言
     * * @return
     */
    @RequestMapping("/user/getVisitorMsg")
    @ResponseBody
    public PageDataTable<Visitor> getVisitorMsg(Visitor param) {
        PageDataTable<Visitor> res = new PageDataTable();
        try {
            List<Visitor> visitors = new ArrayList<>();
            Map<Integer, Visitor> map = Visit.visitorMap;
            for (Integer in : map.keySet()) {
                //map.keySet()返回的是所有key的值
                Visitor visitor = map.get(in);//得到每个key多对用value的值
                visitors.add(visitor);
            }
            res.setiTotalDisplayRecords(map.size());
            res.setsEcho(map.size());
            if ((param.getLength() + param.getStart()) <= visitors.size()) {
                res.setAaData(visitors.subList(param.getStart(), param.getStart() + param.getLength()));
            } else {
                res.setAaData(visitors.subList(param.getStart(), visitors.size()));

            }
        } catch (Exception e) {

        }
        return res;
    }


}
