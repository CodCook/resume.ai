package resume.ai.project.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import resume.ai.project.model.AnalysisRequest;

public class AnalysisEventPublisher {
    private final List<AnalysisObserver> observers = new CopyOnWriteArrayList<>();

    public void subscribe(AnalysisObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(AnalysisObserver observer) {
        observers.remove(observer);
    }

    public void notifyAnalysisCompleted(AnalysisRequest request, String result) {
        for (AnalysisObserver observer : observers) {
            observer.onAnalysisCompleted(request, result);
        }
    }
}