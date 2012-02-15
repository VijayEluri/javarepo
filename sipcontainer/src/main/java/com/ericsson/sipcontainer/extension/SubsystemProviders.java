package com.ericsson.sipcontainer.extension;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.ADD;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.HEAD_COMMENT_ALLOWED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.NAMESPACE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OPERATION_NAME;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REMOVE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.TAIL_COMMENT_ALLOWED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.CHILDREN;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.MAX_OCCURS;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.MIN_OCCURS;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.MODEL_DESCRIPTION;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.ATTRIBUTES;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.TYPE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REQUIRED;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DEFAULT;
import static org.jboss.dmr.ModelType.LONG;


import java.util.Locale;

import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.dmr.ModelNode;

/**
 * Contains the description providers. The description providers are what print out the information when you execute the {@code read-resource-description}
 * operation.
 *
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
class SubsystemProviders {

    /**
     * Used to create the description of the subsystem
     */
    public static DescriptionProvider SUBSYSTEM = new DescriptionProvider() {

        public ModelNode getModelDescription(Locale locale) {
            //The locale is passed in so you can internationalize the strings used in the descriptions

            final ModelNode subsystem = new ModelNode();
            subsystem.get(DESCRIPTION).set("Ericsson SIP Container");
            subsystem.get(HEAD_COMMENT_ALLOWED).set(true);
            subsystem.get(TAIL_COMMENT_ALLOWED).set(true);
            subsystem.get(NAMESPACE).set(SubsystemExtension.NAMESPACE);
            return subsystem;
        }
    };
    /**
     * Used to create the description of the subsystem add method
     */
    public static DescriptionProvider SUBSYSTEM_ADD = new DescriptionProvider() {

        public ModelNode getModelDescription(Locale locale) {
            //The locale is passed in so you can internationalize the strings used in the descriptions

            final ModelNode subsystem = new ModelNode();
            subsystem.get(OPERATION_NAME).set(ADD);
            subsystem.get(DESCRIPTION).set("Adds sipcontainer subsystem");

            return subsystem;
        }
    };
    /**
     * Used to create the description of the subsystem remove method
     */
    public static DescriptionProvider SUBSYSTEM_REMOVE = new DescriptionProvider() {

        public ModelNode getModelDescription(Locale locale) {
            //The locale is passed in so you can internationalize the strings used in the descriptions

            final ModelNode subsystem = new ModelNode();
            subsystem.get(OPERATION_NAME).set(REMOVE);
            subsystem.get(DESCRIPTION).set("Removes sipcontainer subsystem");

            return subsystem;
        }
    };
}
