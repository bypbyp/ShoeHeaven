package io.technicaAssigment.shoeHeaven.controller.web;

import com.mysql.cj.xdevapi.Type;
import io.technicaAssigment.shoeHeaven.command.ShoeDto;
import io.technicaAssigment.shoeHeaven.command.converter.ShoeDtoToShoe;
import io.technicaAssigment.shoeHeaven.command.converter.ShoeToShoeDto;
import io.technicaAssigment.shoeHeaven.exceptions.NoMoreShoeException;
import io.technicaAssigment.shoeHeaven.exceptions.ShoeNotFoundException;
import io.technicaAssigment.shoeHeaven.persistence.model.Shoe;
import io.technicaAssigment.shoeHeaven.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shoe")
public class ShoeController {

    private ShoeDtoToShoe shoeDtoToShoe;
    private ShoeToShoeDto shoeToShoeDto;
    private ShoeService shoeService;

    @Autowired
    public void setShoeService(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @Autowired
    public void setShoeToShoeDto(ShoeToShoeDto shoeToShoeDto) {
        this.shoeToShoeDto = shoeToShoeDto;
    }

    @Autowired
    public void setShoeDtoToShoe(ShoeDtoToShoe shoeDtoToShoe) {
        this.shoeDtoToShoe = shoeDtoToShoe;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listShoe(Model model) {
        List<Shoe> shoeList = shoeService.List();
        model.addAttribute("shoeList", shoeList);
        return "shoe/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showShoe(@PathVariable Integer id, Model model) {

        Shoe shoe = shoeService.get(id);
        model.addAttribute("shoe", shoe);
        return "shoe/show";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String addShoe(Model model) {

        model.addAttribute("shoe", new ShoeDto());
        model.addAttribute("type", Type.values());
        return "shoe/add";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editShoe(@PathVariable Integer id, Model model) {
        model.addAttribute("shoe", shoeToShoeDto.convert(shoeService.get(id)));
        model.addAttribute("type", Type.values());
        return "shoe/update";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action = save")
    public String saveShoe(@Valid @ModelAttribute("shoe") ShoeDto shoeDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "shoe/add-update";
        }

        Shoe savedShoe = shoeService.save(shoeDtoToShoe.convert(shoeDto));
        redirectAttributes.addFlashAttribute("lastAction", "saved" + savedShoe.getName() + " " + savedShoe.getBrand());
        return "redirect:/shoe/" + savedShoe.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action = cancel")
    public String cancelSaveShoe() {
        return "redirect:/shoe/";
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteShoe(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws NoMoreShoeException, ShoeNotFoundException {

        Shoe shoe = shoeService.get(id);
        shoeService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted" + shoe.getName() + shoe.getBrand());
        return "redirect/shoe";
    }
}
