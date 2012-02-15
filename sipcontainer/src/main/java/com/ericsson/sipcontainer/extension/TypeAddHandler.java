/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.sipcontainer.extension;

import java.util.List;
import java.util.Locale;
import org.jboss.as.controller.AbstractAddStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ServiceVerificationHandler;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.dmr.ModelNode;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REQUEST_PROPERTIES;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.TYPE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REQUIRED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DEFAULT;
import static org.jboss.dmr.ModelType.LONG;
import org.jboss.logging.Logger;
import org.jboss.msc.service.ServiceController;

/**
 *
 * @author miguel
 */
public class TypeAddHandler extends AbstractAddStepHandler implements DescriptionProvider {

    public static final TypeAddHandler INSTANCE = new TypeAddHandler();
    
    private final Logger log = Logger.getLogger(TypeAddHandler.class);

    public TypeAddHandler() {
    }

    @Override
    protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
        //The default value is 10000 if it has not been specified
        long tick = 10000;
        //Read the value from the operation
        if (operation.hasDefined("tick")) {
            tick = operation.get("tick").asLong();
        }
        model.get("tick").set(tick);
    }

    @Override
    public ModelNode getModelDescription(Locale locale) {
        ModelNode node = new ModelNode();
        node.get(DESCRIPTION).set("Adds a tracked deployment type");
        node.get(REQUEST_PROPERTIES, "tick", DESCRIPTION).set("How often to output information about a tracked deployment");
        node.get(REQUEST_PROPERTIES, "tick", TYPE).set(LONG);
        node.get(REQUEST_PROPERTIES, "tick", REQUIRED).set(false);
        node.get(REQUEST_PROPERTIES, "tick", DEFAULT).set(10000);
        return node;
    }
    
    @Override
    protected void performRuntime(OperationContext context, ModelNode operation, ModelNode model,
    ServiceVerificationHandler verificationHandler, List<ServiceController<?>> newControllers)
            throws OperationFailedException {
        log.info("TypeAddHandler performRuntime");
         
    }
}
