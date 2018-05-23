package musta.belmo.utils.textutils.frames;

import java.util.Map;

public interface SharedEnvironmentVars {


    default void send(SharedEnvironmentVars to, Map<String, Object> vars) {
        to.receive(to, vars);
    }

    void receive(SharedEnvironmentVars from, Map<String, Object> vars);
}
