package com.example.sweaterExp.controller;

import com.example.sweaterExp.domain.Message;
import com.example.sweaterExp.domain.User;
import com.example.sweaterExp.repo.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private IMessageRepo messageRepo;

    @GetMapping("/check")
    public String greeting(@RequestParam(required = false, defaultValue = "") String filterTag, Model model)
    {
        if (!filterTag.isEmpty() && filterTag != null){
            List<Message> messagesByTag = messageRepo.findByTag(filterTag);
            model.addAttribute("nameModel", messagesByTag);
            return "greetingPage";
        }
        Iterable<Message> pollMessages = messageRepo.findAll();
        model.addAttribute("nameModel", pollMessages);
        model.addAttribute("filterTag", filterTag);
        return "greetingPage";
    }

    @GetMapping("/pups")
    public String godPups(@RequestParam(name = "nameURL", required = false, defaultValue = "my Lord") String name, Map<String, Object> model){
        model.put("name", name + " is allive");
        return "pupsGod";
    }


    @PostMapping("/check")
    public String addAll(
            @RequestParam String textMessage,
            @RequestParam String tag,
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam("File")MultipartFile multipartFile) throws IOException {

        Message message = new Message(textMessage, tag, user);

        if (!multipartFile.isEmpty()){
            File uplDir = new File(uploadPath);
        if (!uplDir.exists()){
            uplDir.mkdir();
        }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + multipartFile.getOriginalFilename();

            multipartFile.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);

        }

        messageRepo.save(message);
        Iterable<Message> pollMessages = messageRepo.findAll();
        model.addAttribute("nameModel", pollMessages);
        return "greetingPage";
    }
}

