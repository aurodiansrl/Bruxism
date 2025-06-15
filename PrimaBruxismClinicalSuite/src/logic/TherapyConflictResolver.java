// src/logic/TherapyConflictResolver.java
package logic;

import java.util.*;

// import logic.TherapyPlanner.TherapyOption; // Explicitly import TherapyOption

public class TherapyConflictResolver {

    public static class Conflict {
        public final TherapyPlanner.TherapyOption option1;
        public final TherapyPlanner.TherapyOption option2;
        public final String reason;

        public Conflict(TherapyPlanner.TherapyOption option1, TherapyPlanner.TherapyOption option2, String reason) {
            this.option1 = option1;
            this.option2 = option2;
            this.reason = reason;
        }

        @Override
        public String toString() {
            return "Conflict between [" + option1.therapy + "] and [" + option2.therapy + "]: " + reason;
        }
    }

    private final List<TherapyPlanner.DomainInteraction> interactionRules;

    public TherapyConflictResolver(List<TherapyPlanner.DomainInteraction> interactionRules) {
        this.interactionRules = interactionRules;
    }

    public List<Conflict> findConflicts(List<TherapyPlanner.TherapyOption> therapyOptions) {
        List<Conflict> conflicts = new ArrayList<>();

        for (int i = 0; i < therapyOptions.size(); i++) {
            for (int j = i + 1; j < therapyOptions.size(); j++) {
                TherapyPlanner.TherapyOption t1 = therapyOptions.get(i);
                TherapyPlanner.TherapyOption t2 = therapyOptions.get(j);

                Optional<TherapyPlanner.DomainInteraction> match = interactionRules.stream()
                        .filter(rule -> rule.primary.equalsIgnoreCase(t1.domain)
                                && rule.secondary.equalsIgnoreCase(t2.domain)
                                && ((String) rule.relation).equalsIgnoreCase("conflict"))
                        .findFirst();

                if (match.isPresent()) {
                    conflicts.add(new Conflict(t1, t2, match.get().action));
                }
            }
        }

        return conflicts;
    }

    public List<TherapyPlanner.TherapyOption> resolveConflicts(List<TherapyPlanner.TherapyOption> original, List<Conflict> conflicts) {
        Set<String> toRemove = new HashSet<>();
        for (Conflict conflict : conflicts) {
            // In questo esempio rimuoviamo il secondo therapy, ma logica può essere più complessa
            toRemove.add(conflict.option2.therapy);
        }

        List<TherapyPlanner.TherapyOption> resolved = new ArrayList<>();
        for (TherapyPlanner.TherapyOption opt : original) {
            if (!toRemove.contains(opt.therapy)) {
                resolved.add(opt);
            }
        }
        return resolved;
    }
}
