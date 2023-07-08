/**
 * OntologyManagerSoapServiceProvider_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ilc.vikef.ontologymanager;

public interface OntologyManagerSoapServiceProvider_PortType extends java.rmi.Remote {
    public java.lang.String[] getProperties(java.lang.String userId, java.lang.String password, java.lang.String ontologyVersionId, java.lang.String domainConcept, java.lang.String rangeConcept, boolean domainDirectProperties) throws java.rmi.RemoteException;
    public java.lang.String querySrnVersion(java.lang.String userId, java.lang.String password, java.lang.String srnVersionId, java.lang.String rdqlQueryString, java.lang.String serializationLang) throws java.rmi.RemoteException;
    public java.lang.String getOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String lang) throws java.rmi.RemoteException;
    public java.lang.String[] listOntologyVersions(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public java.lang.String getOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, java.lang.String lang) throws java.rmi.RemoteException;
    public boolean isReadableOntology(java.lang.String user, java.lang.String password, java.lang.String groupIdOrNull, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public java.lang.String[] listOntologies(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] listAllPublishedOntologyVersions(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public boolean isPublishedOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String version) throws java.rmi.RemoteException;
    public java.lang.String[] listPublishedOntologyVersions(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public java.lang.String addOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String ontology) throws java.rmi.RemoteException;
    public boolean updateOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String versionId, java.lang.String serializedOntology) throws java.rmi.RemoteException;
    public boolean isWritableOntology(java.lang.String user, java.lang.String password, java.lang.String groupIdOrNull, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean deleteOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean deleteOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String versionId) throws java.rmi.RemoteException;
    public boolean lockOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean unlockOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean isLockedOntology(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public java.lang.String getOntologyLock(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean publishOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String versionId) throws java.rmi.RemoteException;
    public boolean unpublishOntologyVersion(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String versionId) throws java.rmi.RemoteException;
    public java.lang.String getOntologyHistory(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public java.lang.String[] listAllUnpublishedOntologyVersions(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] listUnpublishedOntologyVersions(java.lang.String user, java.lang.String password, java.lang.String ontologyId) throws java.rmi.RemoteException;
    public boolean manageOntologyAccessRights(java.lang.String user, java.lang.String password, java.lang.String ontologyId, java.lang.String userGroupID, boolean readEnabled, boolean writeEnabled) throws java.rmi.RemoteException;
    public java.lang.String queryOntologyVersionSPARQL(java.lang.String userId, java.lang.String password, java.lang.String ontoVersionId, java.lang.String sparqlQueryString, java.lang.String serializationLang) throws java.rmi.RemoteException;
    public java.lang.String addSRN(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String ontologyVersionId, java.lang.String srnSerialization) throws java.rmi.RemoteException;
    public java.lang.String[] listSRNs(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] listSRNVersions(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public java.lang.String getSRN(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String lang) throws java.rmi.RemoteException;
    public boolean deleteSRN(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean deleteSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String versionId) throws java.rmi.RemoteException;
    public java.lang.String getSRNHistory(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public java.lang.String getSRNLock(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public java.lang.String getSRNVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, java.lang.String lang) throws java.rmi.RemoteException;
    public boolean isLockedSRN(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean isPublishedSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String versionId) throws java.rmi.RemoteException;
    public java.lang.String[] listAllPublishedSRNVersions(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] listPublishedSRNVersions(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public java.lang.String[] listAllUnpublishedSRNVersions(java.lang.String user, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] listUnpublishedSRNVersions(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean lockSRN(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean publishSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String versionId) throws java.rmi.RemoteException;
    public boolean unlockSRN(java.lang.String user, java.lang.String password, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean unpublishSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String versionId) throws java.rmi.RemoteException;
    public boolean updateSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnId, java.lang.String versionId, java.lang.String srn) throws java.rmi.RemoteException;
    public java.lang.String[] listSRNIDsHavingOntologyVersionID(java.lang.String user, java.lang.String password, java.lang.String ontologyVersionId) throws java.rmi.RemoteException;
    public java.lang.String[] listSRNVersionIDsHavingOntologyVersionID(java.lang.String user, java.lang.String password, java.lang.String ontologyVersionId) throws java.rmi.RemoteException;
    public boolean addTripleToSRNVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, java.lang.String subject, java.lang.String predicate, java.lang.String object, boolean isObjectLiteral) throws java.rmi.RemoteException;
    public ilc.vikef.ontologymanager.Triple[] addTriplesToSRNVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, ilc.vikef.ontologymanager.Triple[] triplearr) throws java.rmi.RemoteException;
    public boolean deleteTripleFromSRNVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, java.lang.String subject, java.lang.String predicate, java.lang.String object, boolean isObjectLiteral) throws java.rmi.RemoteException;
    public ilc.vikef.ontologymanager.Triple[] deleteTriplesFromSRNVersion(java.lang.String user, java.lang.String password, java.lang.String versionId, ilc.vikef.ontologymanager.Triple[] triplearr) throws java.rmi.RemoteException;
    public boolean updateTripleInSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnVersionId, java.lang.String sourceSubject, java.lang.String sourcePredicate, java.lang.String sourceObject, boolean sourceIsObjectLiteral, java.lang.String destSubject, java.lang.String destPredicate, java.lang.String destObject, boolean destIsObjectLiteral) throws java.rmi.RemoteException;
    public ilc.vikef.ontologymanager.Triple[] updateTriplesInSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnVersionId, ilc.vikef.ontologymanager.Triple[] triplearr) throws java.rmi.RemoteException;
    public java.lang.String getTripleFromSRNVersion(java.lang.String user, java.lang.String password, java.lang.String srnVersionId, java.lang.String subject, java.lang.String predicate, java.lang.String object, boolean isObjectLiteral, java.lang.String lang) throws java.rmi.RemoteException;
    public boolean isReadableSrn(java.lang.String user, java.lang.String password, java.lang.String groupIdOrNull, java.lang.String srnId) throws java.rmi.RemoteException;
    public boolean isWritableSrn(java.lang.String user, java.lang.String password, java.lang.String groupIdOrNull, java.lang.String srnId) throws java.rmi.RemoteException;
    public java.lang.String querySrnVersionSPARQL(java.lang.String userId, java.lang.String password, java.lang.String srnVersionId, java.lang.String sparqlQueryString, java.lang.String serializationLang) throws java.rmi.RemoteException;
    public java.lang.String queryResultsSrnVersion(java.lang.String userId, java.lang.String password, java.lang.String srnVersionId, java.lang.String rdqlQueryString, java.lang.String serializationLang) throws java.rmi.RemoteException;
    public boolean manageSRNAccessRights(java.lang.String userId, java.lang.String password, java.lang.String srnId, java.lang.String groupId, boolean readEnabled, boolean writeEnabled) throws java.rmi.RemoteException;
    public java.lang.String getOntologyVersionIdOfSrnVersion(java.lang.String userId, java.lang.String password, java.lang.String srnVersionId) throws java.rmi.RemoteException;
}
