package ru.levelp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.myapp.dao.PartsDAO;
import ru.levelp.myapp.model.Part;
import javax.servlet.http.HttpSession;

@Controller
public class AddPartController {

    @Autowired
    private PartsDAO dao;

    @Autowired
    private AddPartBean bean;

    @GetMapping("/add-part")
    public String addPartPage(HttpSession session, ModelMap model){
        model.addAttribute("addPartBean", bean);
        return "addPart";
    }

    //@RequestParam - спринг из запроса, отравленного в с формы вытащит параметры и подставит их сюда
    //@RequestMapping dispatcher servlet сам найдет этот метод для пути паф и метода пост и сам будет в нужный момент переправлять
    // запросы сюда
    // model
    @RequestMapping(path = "/add-part", method = RequestMethod.POST)
    public String postAddPart (@RequestParam String partId, @RequestParam String title,
                               @RequestParam int supplierId, ModelMap model, HttpSession session){


//        session.getAttribute("userName");
//        session.setAttribute("userName", "root");

        Part part;

        try{
            part = dao.createPart(partId, title, supplierId);
        } catch (Exception e) {
            dao.getEntityManager().getTransaction().rollback();
            e.printStackTrace();
            return e.getMessage();
        }

        AddPartCompliteBean bean = new AddPartCompliteBean(part.getPartId(), part.getTitle(), part.getSupplier().getName());
        model.addAttribute("bean", bean);
//        возващаем имя вьюхи, которую хотим показать
        return "AddPartCompilete";

    }

}
