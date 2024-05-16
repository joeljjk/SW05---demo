package ch.zhaw.iwi.devops.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LogbookController {

    private List<LogbookEntry> logbook = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Logbook Service is up and running!");
    }

    @GetMapping("/logbook")
    public List<LogbookEntry> getAllEntries() {
        return logbook;
    }

    @GetMapping("/logbook/{id}")
    public LogbookEntry getEntry(@PathVariable int id) {
        return logbook.stream()
                      .filter(entry -> entry.getId() == id)
                      .findFirst()
                      .orElse(null);
    }

    @PostMapping("/logbook")
    public void addEntry(@RequestBody String message) {
        int id = idCounter.incrementAndGet();
        LogbookEntry newEntry = new LogbookEntry(id, message, LocalDateTime.now());
        logbook.add(newEntry);
    }

    @DeleteMapping("/logbook")
    public void deleteMostRecentEntry() {
        if (!logbook.isEmpty()) {
            logbook.remove(logbook.size() - 1);
        }
    }

    private static class LogbookEntry {
        private int id;
        private String message;
        private LocalDateTime timestamp;

        public LogbookEntry(int id, String message, LocalDateTime timestamp) {
            this.id = id;
            this.message = message;
            this.timestamp = timestamp;
        }

        public int getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}