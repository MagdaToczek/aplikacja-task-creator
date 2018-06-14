package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("company", adminConfig.getCompanyName() + ", " + adminConfig.getCompanyAddress());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tutorial_url", "https://www.tutorialspoint.com/mysql/index.htm/");
        context.setVariable("button", "Learn about MySQL");
        context.setVariable("preview_message", "Are you curious what's the score?");
        context.setVariable("goodbye_message", "Best regards");
        context.setVariable("company", adminConfig.getCompanyName() + ", " + adminConfig.getCompanyAddress());
        context.setVariable("show_button", adminConfig.getDbType().equals("mysql"));
        context.setVariable("is_emptyDb", taskRepository.count() == 0);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/scheduled-mail", context);
    }
}
