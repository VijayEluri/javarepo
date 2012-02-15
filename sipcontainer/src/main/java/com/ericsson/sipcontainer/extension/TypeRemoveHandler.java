/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericsson.sipcontainer.extension;

import java.util.Locale;
import org.jboss.as.controller.AbstractRemoveStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceName;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIPTION;

/**
 *
 * @author miguel
 */
public class TypeRemoveHandler extends AbstractRemoveStepHandler implements DescriptionProvider {

    public static final TypeRemoveHandler INSTANCE = new TypeRemoveHandler();

    private TypeRemoveHandler() {
    }

    @Override
    public ModelNode getModelDescription(Locale locale) {
        ModelNode node = new ModelNode();
        node.get(DESCRIPTION).set("Removes a tracked deployment type");
        return node;
    }

    @Override
    protected void performRuntime(OperationContext context, ModelNode operation, ModelNode model) throws OperationFailedException {
        String suffix = PathAddress.pathAddress(operation.get(ModelDescriptionConstants.ADDRESS)).getLastElement().getValue();
        ServiceName name = SipContainerService.createServiceName(suffix);
        context.removeService(name);
    }
}