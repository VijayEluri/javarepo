package com.ericsson.sipcontainer.extension;

import com.ericsson.sipcontainer.deployment.SipContextFactoryDeploymentProcessor;
import java.util.List;

import org.jboss.as.controller.AbstractBoottimeAddStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ServiceVerificationHandler;
import org.jboss.as.server.AbstractDeploymentChainStep;
import org.jboss.as.server.DeploymentProcessorTarget;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;
import org.jboss.msc.service.ServiceController;

import com.ericsson.sipcontainer.deployment.SipMetaDataDeploymentProcessor;
import org.jboss.as.server.deployment.Phase;

/**
 * Handler responsible for adding the subsystem resource to the model
 *
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
class SubsystemAdd extends AbstractBoottimeAddStepHandler {

    static final SubsystemAdd INSTANCE = new SubsystemAdd();
    
    static int DEPLOYMENT_PROCESS_PRIORITY = 0x4000;

    private final Logger log = Logger.getLogger(SubsystemAdd.class);

    private SubsystemAdd() {
    }

    /** {@inheritDoc} */
    @Override
    protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
        log.info("Populating the model");
        model.setEmptyObject();
    }

    /** {@inheritDoc} */
    @Override
    public void performBoottime(OperationContext context, ModelNode operation, ModelNode model,
            ServiceVerificationHandler verificationHandler, List<ServiceController<?>> newControllers)
            throws OperationFailedException {

        //Add deployment processors here
        //Remove this if you don't need to hook into the deployers, or you can add as many as you like
        //see SubDeploymentProcessor for explanation of the phases
        context.addStep(new AbstractDeploymentChainStep() {
            public void execute(DeploymentProcessorTarget processorTarget) {
                //processorTarget.addDeploymentProcessor(SubsystemDeploymentProcessor.PHASE, SubsystemDeploymentProcessor.PRIORITY, new SubsystemDeploymentProcessor());
                // Add the SIP specific deployment processor
                processorTarget.addDeploymentProcessor(Phase.PARSE, DEPLOYMENT_PROCESS_PRIORITY, SipMetaDataDeploymentProcessor.INSTANCE);
                // Add the context in a different phase
                processorTarget.addDeploymentProcessor(Phase.POST_MODULE, DEPLOYMENT_PROCESS_PRIORITY, SipContextFactoryDeploymentProcessor.INSTANCE);

            }
        }, OperationContext.Stage.RUNTIME);

    }
}
