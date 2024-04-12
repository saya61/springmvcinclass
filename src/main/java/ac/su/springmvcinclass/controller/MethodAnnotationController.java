package ac.su.springmvcinclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/method-test")
public class MethodAnnotationController {
    @RequestMapping(value = "/get1", method = RequestMethod.GET)
    @ResponseBody
    public String get1() {
        return "<h3>This is GET method response 1</h3>";
    }

    @GetMapping("/get2")
    @ResponseBody
    public String get2() {
        return "<h3>This is GET method response 2</h3>";
    }
    
    @PostMapping("/post")
    @ResponseBody
    public String post() {
        return "<h3>This is POST method response 3</h3>";
    }

    @PutMapping("/put")
    @ResponseBody
    public String put() {
        return "<h3>This is PUT method response 3</h3>";
    }

    @PatchMapping("/patch")
    @ResponseBody
    public String patch() {
        return "<h3>This is PATCH method response 3</h3>";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete() {
        return "<h3>This is DELETE method response 3</h3>";
    }

}
