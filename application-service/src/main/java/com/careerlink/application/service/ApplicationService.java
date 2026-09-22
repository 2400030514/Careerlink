package com.careerlink.application.service;

import com.careerlink.application.entity.Application;
import com.careerlink.application.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplication(Long id, Application application) {

        Optional<Application> existing = applicationRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        Application app = existing.get();

        app.setJobId(application.getJobId());
        app.setProfileId(application.getProfileId());
        app.setStatus(application.getStatus());

        return applicationRepository.save(app);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}