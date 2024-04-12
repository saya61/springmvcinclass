package ac.su.springmvcinclass.controller;

import ac.su.springmvcinclass.domain.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/req-data-check")
public class ReqDataController {
    @GetMapping("/example1")
    @ResponseBody
    public String example1(HttpServletRequest request) {
        String param1 = request.getParameter("param1");
        int param2 = Integer.parseInt(request.getParameter("param2"));
        return param1 + " / " + param2;
    }

    @GetMapping("/example2-form")
    public String showForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login-form";
    }

    @PostMapping("/example2-submit")
    public String submitForm(@ModelAttribute("loginForm") LoginForm formData) {
        System.out.println("Email: " + formData.getEmail() + " / Password: " + formData.getPassword());
        return "submit-result";
    }

    // URL path값 참조
    @GetMapping("/example3-pathvar/{param1}/{param2}")
    @ResponseBody
    public String example3PathVar(@PathVariable String param1,
                                  @PathVariable String param2) {
        return param1 + " / " + param2;
    }

    // Query String의 값 참조
    @GetMapping("/example3-reqparam")
    @ResponseBody
    public String example3Param(@RequestParam("param1") String param1,
                                @RequestParam("param2") int param2)
    {
        return param1 + " / " + param2;
    }

    // 속성을 이용하여 기본값 지정 가능
    @GetMapping("/example3-reqparam2")
    @ResponseBody
    public String example3Param2(@RequestParam(value = "param1", defaultValue = "param1 is missing") String param1,
                                @RequestParam(value = "param2", defaultValue = "0") int param2)
    {
        return param1 + " / " + param2;
    }

    // 요청 body에서 수신한 payload 값 참조 가능
    @PostMapping("/example3-reqbody")
    @ResponseBody
    public String example3Body(@RequestBody String payload) {
        return "Received payload: " + payload;
    }
}
