package com.ericsson.sipcontainer.extension;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.ADD;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.DESCRIBE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP_ADDR;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.REMOVE;
import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;

import java.util.List;
import java.util.Locale;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;

import org.jboss.as.controller.Extension;
import org.jboss.as.controller.ExtensionContext;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.OperationStepHandler;
import org.jboss.as.controller.PathAddress;
import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SubsystemRegistration;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.as.controller.descriptions.ModelDescriptionConstants;
import org.jboss.as.controller.descriptions.common.CommonDescriptions;
import org.jboss.as.controller.parsing.ExtensionParsingContext;
import org.jboss.as.controller.parsing.ParseUtils;
import org.jboss.as.controller.persistence.SubsystemMarshallingContext;
import org.jboss.as.controller.registry.AttributeAccess.Storage;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.as.controller.registry.OperationEntry;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.jboss.staxmapper.XMLElementReader;
import org.jboss.staxmapper.XMLElementWriter;
import org.jboss.staxmapper.XMLExtendedStreamReader;
import org.jboss.staxmapper.XMLExtendedStreamWriter;

/**
 *
 * @author <a href="kabir.khan@jboss.com">Kabir Khan</a>
 */
public class SubsystemExtension implements Extension {

    /**
     * The name space used for the {@code substystem} element
     */
    public static final String NAMESPACE = "urn:com.ericsson.sipcontainer:1.0";
    /**
     * The name of our subsystem within the model.
     */
    public static final String SUBSYSTEM_NAME = "sipcontainer";
    /**
     * The parser used for parsing our subsystem
     */
    private final SubsystemParser parser = new SubsystemParser();

    @Override
    public void initializeParsers(ExtensionParsingContext context) {
        context.setSubsystemXmlMapping(NAMESPACE, parser);
    }

    @Override
    public void initialize(ExtensionContext context) {
        final SubsystemRegistration subsystem = context.registerSubsystem(SUBSYSTEM_NAME);
        final ManagementResourceRegistration registration = subsystem.registerSubsystemModel(SubsystemProviders.SUBSYSTEM);
        //We always need to add an 'add' operation
        registration.registerOperationHandler(ADD, SubsystemAdd.INSTANCE, SubsystemProviders.SUBSYSTEM_ADD, false);
        //Every resource that is added, normally needs a remove operation
        registration.registerOperationHandler(REMOVE, SubsystemRemove.INSTANCE, SubsystemProviders.SUBSYSTEM_REMOVE, false);
        //We always need to add a 'describe' operation
        registration.registerOperationHandler(DESCRIBE, SubsystemDescribeHandler.INSTANCE, SubsystemDescribeHandler.INSTANCE, false, OperationEntry.EntryType.PRIVATE);

        //Add the type child
//        ManagementResourceRegistration typeChild = registration.registerSubModel(PathElement.pathElement("type"), SubsystemProviders.TYPE_CHILD);
//        typeChild.registerOperationHandler(ADD, TypeAddHandler.INSTANCE, TypeAddHandler.INSTANCE);
//        typeChild.registerOperationHandler(REMOVE, TypeRemoveHandler.INSTANCE, TypeRemoveHandler.INSTANCE);
//        typeChild.registerReadWriteAttribute("tick", null, SipContainerHandler.INSTANCE, Storage.CONFIGURATION);

        subsystem.registerXMLElementWriter(parser);
    }

    private static ModelNode createAddSubsystemOperation() {
        final ModelNode subsystem = new ModelNode();
        subsystem.get(OP).set(ADD);
        subsystem.get(OP_ADDR).add(SUBSYSTEM, SUBSYSTEM_NAME);
        return subsystem;
    }

    /**
     * The subsystem parser, which uses stax to read and write to and from xml
     */
    private static class SubsystemParser implements XMLStreamConstants, XMLElementReader<List<ModelNode>>, XMLElementWriter<SubsystemMarshallingContext> {

        /**
         * {@inheritDoc}
         */
        @Override
        public void writeContent(XMLExtendedStreamWriter writer, SubsystemMarshallingContext context) throws XMLStreamException {
            context.startSubsystemElement(SubsystemExtension.NAMESPACE, false);
            writer.writeEndElement();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void readElement(XMLExtendedStreamReader reader, List<ModelNode> list) throws XMLStreamException {
            // Require no content
            ParseUtils.requireNoContent(reader);
            list.add(createAddSubsystemOperation());
        }
    }

    /**
     * Recreate the steps to put the subsystem in the same state it was in. This is used in domain mode to query the profile
     * being used, in order to get the steps needed to create the servers
     */
    private static class SubsystemDescribeHandler implements OperationStepHandler, DescriptionProvider {

        static final SubsystemDescribeHandler INSTANCE = new SubsystemDescribeHandler();

        public void execute(OperationContext context, ModelNode operation) throws OperationFailedException {
            context.getResult().add(createAddSubsystemOperation());

            //Add the operations to create each child

            ModelNode node = context.readModel(PathAddress.EMPTY_ADDRESS);
            for (Property property : node.get("type").asPropertyList()) {

                ModelNode addType = new ModelNode();
                addType.get(OP).set(ModelDescriptionConstants.ADD);
                PathAddress addr = PathAddress.pathAddress(PathElement.pathElement(SUBSYSTEM, SUBSYSTEM_NAME), PathElement.pathElement("type", property.getName()));
                addType.get(OP_ADDR).set(addr.toModelNode());
                if (property.getValue().hasDefined("tick")) {
                    addType.get("tick").set(property.getValue().get("tick").asLong());
                }
                context.getResult().add(addType);
            }

            context.completeStep();
        }

        @Override
        public ModelNode getModelDescription(Locale locale) {
            return CommonDescriptions.getSubsystemDescribeOperation(locale);
        }
    }
}
