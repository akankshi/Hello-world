/*package gov.state.nextgen.co.bo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import gov.state.nextgen.co.util.CONoticeUtility;
import gov.state.nextgen.co.util.CoEdNoticeConstants;
import gov.state.nextgen.co.util.NOEMedicaid;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitApprovalInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitApprovedMemberInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitApprovedMembers;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitChangeInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitChangedMemberInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitChangedMembers;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitClosedMemberInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitClosedMembers;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitClosureInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitDenialInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitDeniedMemberInfo;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.BenefitDeniedMembers;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.EligibilityDetermination;
import gov.state.nextgen.co.util.xsd.schema.eligibilitynotices.NoticeAttachment;
import gov.state.nextgen.co.util.xsd.schema.othernotices.ChimesCorrespondence;
import gov.state.nextgen.co.util.xsd.schema.othernotices.ExplanationOfBenefits;
import gov.state.nextgen.co.util.xsd.schema.othernotices.HouseholdGilReporting;
import gov.state.nextgen.co.util.xsd.schema.othernotices.MetaData;
import gov.state.nextgen.co.util.xsd.schema.othernotices.NoticeData;
import gov.state.nextgen.co.util.xsd.schema.othernotices.NoticeOfEligibility;
import gov.state.nextgen.co.util.xsd.schema.othernotices.NoticeOfEligibilityHCC;
import gov.state.nextgen.co.util.xsd.schema.othernotices.NoticeOfEligibilityHCCTable;
import gov.state.nextgen.co.util.xsd.schema.othernotices.TelephoneAssitanceCert;
import gov.state.nextgen.co.util.xsd.schema.othernotices.TextTable;
import gov.state.nextgen.common.bo.CoDAOServices;
import gov.state.nextgen.common.cargo.custom.COCorrespondence;
import gov.state.nextgen.common.cargo.custom.CoNodRequestDetailCargo;
import gov.state.nextgen.common.cargo.custom.CoRequestHistoryCargo;
import gov.state.nextgen.common.cargo.custom.DcCaseIndividualCargo;
import gov.state.nextgen.common.cargo.custom.DcCasesCargo;
import gov.state.nextgen.common.cargo.custom.DcDisabilityCargo;
import gov.state.nextgen.common.cargo.custom.DcIndvCargo;
import gov.state.nextgen.common.cargo.custom.EdEligFsBudgetCargo;
import gov.state.nextgen.common.cargo.custom.EdEligNoticeReasonsCargo;
import gov.state.nextgen.common.cargo.custom.EdEligibilityCargo;
import gov.state.nextgen.common.cargo.custom.VCoManualDataValuesCargo;
import gov.state.nextgen.common.collection.custom.DcCasesCollection;
import gov.state.nextgen.common.exception.CoException;
import gov.state.nextgen.common.exception.NoDataFoundException;
import gov.state.nextgen.common.util.CoConstants;
import gov.state.nextgen.common.util.CoDebugger;
import gov.state.nextgen.common.util.CorrespondenceServices;
import gov.state.nextgen.common.util.ReferenceTableAccess;
import gov.state.nextgen.framework.business.entities.cargo.custom.RefTableData;

*//**
 * The class <code> NCH001Assembler </code> contains methods (finders) to
 * populate values in the notice
 * "NCH001Assembler - Eligibility Determination Notice"
 * 
 * @author vamrit
 * @version %Revision%
 * 
 *//*
public class NCH001Assembler extends DCAssembler implements CoBaseAssembler {

	*//**
	 * @param args
	 *//*

	public NCH001Assembler() {
		super();
	}
	HashMap<String, String> indvIdNameMap = null;
	@Override
	*//**
	 * Method to populate the values for the notice
	 * @author vamrit 
	 * @param coCorrespondence
	 * @return NDCorrespondence
	 * @throws CoException
	 * 
	 *//*
	
	public ChimesCorrespondence getChimesCorrespondence(COCorrespondence coCorrespondence) throws CoException {

		CoDebugger.debugInformation("START running Assembler for NoED at: "+System.currentTimeMillis()); 
		// object variables for xml binding
		ChimesCorrespondence chimesCorrespondence = new ChimesCorrespondence();
		MetaData metaData = CONoticeUtility.getMetaData(coCorrespondence, CoConstants.ED_COMM_100);
		NoticeData noticeData = new NoticeData();
		//Forms attachment
		HouseholdGilReporting householdGil = new HouseholdGilReporting();
		householdGil=householdGilReportingAttachment(coCorrespondence,metaData);
		noticeData.setHouseholdGilReporting(householdGil);
		
		EligibilityDetermination eligibilityDetermination = new EligibilityDetermination();
		BenefitDeniedMembers benefitDeniedMembers = new BenefitDeniedMembers();
		BenefitClosedMembers benefitClosedMembers = new BenefitClosedMembers();
		BenefitApprovedMembers benefitApprovedMembers = new BenefitApprovedMembers();
		BenefitChangedMembers benefitChangedMembers = new BenefitChangedMembers();
		BenefitDeniedMemberInfo benefitDeniedMemberInfo = new BenefitDeniedMemberInfo();
		BenefitClosedMemberInfo benefitClosedMemberInfo = new BenefitClosedMemberInfo();
		BenefitApprovedMemberInfo benefitApprovedMemberInfo = new BenefitApprovedMemberInfo();
		BenefitChangedMemberInfo benefitChangedMemberInfo = new BenefitChangedMemberInfo();
		BenefitDenialInfo benefitDenialInfo = new BenefitDenialInfo();
		BenefitClosureInfo benefitClosureInfo = new BenefitClosureInfo();
		BenefitApprovalInfo benefitApprovalInfo = new BenefitApprovalInfo();
		BenefitChangeInfo benefitChangeInfo = new BenefitChangeInfo();
		List<BenefitDeniedMemberInfo> deniedMembers = new ArrayList<BenefitDeniedMemberInfo>();
		List<BenefitClosedMemberInfo> closedMembers = new ArrayList<BenefitClosedMemberInfo>();
		List<BenefitApprovedMemberInfo> approvedMembers = new ArrayList<BenefitApprovedMemberInfo>();
		List<BenefitChangedMemberInfo> changedMembers = new ArrayList<BenefitChangedMemberInfo>();
		List<BenefitDenialInfo> benefitDenialInfos = new ArrayList<BenefitDenialInfo>();
		List<BenefitClosureInfo> benefitClosureInfos = new ArrayList<BenefitClosureInfo>();
		List<BenefitApprovalInfo> benefitApprovalInfos = new ArrayList<BenefitApprovalInfo>();
		List<BenefitChangeInfo> benefitChangeInfos = new ArrayList<BenefitChangeInfo>();
		*//**R2**//*
		NoticeOfEligibility  noticeOfEligibility =new NoticeOfEligibility();
		NoticeOfEligibilityHCC eligibilityHCC=new NoticeOfEligibilityHCC();
		NoticeOfEligibilityHCCTable eligibilityHCCTable=new NoticeOfEligibilityHCCTable();
		ArrayList<NoticeOfEligibilityHCCTable> eligibilityHCCTables = new ArrayList<NoticeOfEligibilityHCCTable>();
		List<ExplanationOfBenefits>explanationTextsList=new ArrayList<ExplanationOfBenefits>();
		ExplanationOfBenefits explanationText=new ExplanationOfBenefits();
		HashMap<String,String> pointsMap=new HashMap<String,String>();
		HashMap<String,ExplanationOfBenefits> infoMap=new HashMap<String,ExplanationOfBenefits>();
		HashMap<String,ArrayList<NoticeOfEligibilityHCCTable>> finalHccMap=new HashMap<String,ArrayList<NoticeOfEligibilityHCCTable>>();
		List<NoticeOfEligibilityHCCTable> fianleligibilityHCCTables = new ArrayList<NoticeOfEligibilityHCCTable>();
		String asOfDate = coCorrespondence.getAsOfDate();
		NOEMedicaid noeMedicaid =new NOEMedicaid();
		String displayHcc="N";
		String displaySNAP="N";
		String mspApproved=null;
		HashMap<String,List<EdEligibilityCargo>> mspApprovedMap=new HashMap<String,List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> deniedIndvIdEdEligibilityCargosSNAPMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> closedIndvIdEdEligibilityCargosSNAPMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> approvedIndvIdEdEligibilityCargosSNAPMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> changedIndvIdEdEligibilityCargosMapSNAP = new HashMap<String, List<EdEligibilityCargo>>();

		// DAOs and Cargos
		CoDAOServices coDaoServices = new CoDAOServices();
		EdEligibilityCargo[] edEligibilityCargos = null;
		List<EdEligibilityCargo> edEligibilityCargoList=null;
		EdEligibilityCargo[] edEligibilityCargosDes = null;
		// EdEligNoticeReasonsCargo[] edEligNoticeReasonsCargos = null;

		// local variables
		List<String> allEdgTraceIdsAsList = new ArrayList<String>();
		ArrayList<Long> indvIds = new ArrayList<Long>();
		HashSet<String> individualIds = new HashSet<String>();
		String edgTraceIdsStringForQuery = "";
		HashMap<String, ArrayList<String>> edgTraceIdNoticeReasonCodeMap = null;
		HashMap<String, RefTableData> noticeReasonCodeRefDataMap = null;
		
		HashMap<String, String> coverageCodeDescriptionMap = null;
		HashMap<String, String> noticeFieldCodeDescriptionMap = null;
		HashMap<String, List<EdEligibilityCargo>> indvIdEdEligibilityCargosMap = null;
		HashMap<String, List<EdEligibilityCargo>> deniedIndvIdEdEligibilityCargosMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> closedIndvIdEdEligibilityCargosMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> approvedIndvIdEdEligibilityCargosMap = new HashMap<String, List<EdEligibilityCargo>>();
		HashMap<String, List<EdEligibilityCargo>> changedIndvIdEdEligibilityCargosMap = new HashMap<String, List<EdEligibilityCargo>>();
		String displayDeniedTable = CoConstants.N;
		String displayClosedTable = CoConstants.N;
		String displayApprovedTable = CoConstants.N;
		String displayChangedTable = CoConstants.N;
		String displayNoticeAttachment = CoConstants.N;
		String additionalComments = CoConstants.SPACE;
		String legalCites = CoConstants.SPACE;
		boolean authorizedBefore = false;
		boolean intakeFlag = false;		
		boolean retroAppCheck = false;
		// information from coCorrespondence object
		String docId = coCorrespondence.getDocId();
		String caseNumber = coCorrespondence.getCaseAppNumber();
		long coReqSeq = coCorrespondence.getCoReqSeq();
		boolean manuallyGenrated = coCorrespondence.getIsManualyGenerated();
		Date generateDate = new Date(coCorrespondence.getGenerateDate().getTime());
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String generateDateString = "";
		CoDebugger.debugInformation("Assembler processing NoED for case: "+caseNumber+" at: "+System.currentTimeMillis()); 
		try{
			generateDateString = dateFormat.format(generateDate);
		}catch(Exception e){
			CoDebugger.debugException(e.getMessage(), e);
		}

		individualIds = getAllActiveInCaseIndividuals(caseNumber, generateDateString);
		
		try{
			Object[] objlist1 = new Object[1];
			DcCasesCargo dcCaseCargo = new DcCasesCargo();
			dcCaseCargo.setCaseNum(Long.parseLong(caseNumber));
			objlist1[0] = dcCaseCargo;
			DcCasesCollection dcCasesCol = new DcCasesCollection();
			DcCasesCargo[] dcCaseCargoArr = (DcCasesCargo[]) dcCasesCol.select("findByCaseNumForLastInitiateReview", objlist1);
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> size --> " + dcCaseCargoArr.length);
			CoDebugger.debugInformation("----USERACTION---- "+dcCaseCargoArr[1].getUserActionCd());
			if(null != dcCaseCargoArr && null != dcCaseCargoArr[1].getUserActionCd() && "PI".equalsIgnoreCase(dcCaseCargoArr[1].getUserActionCd())){
					intakeFlag = true;
			}
			}catch(Exception e){
				CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while fetching from audit");
			}

		try {
			if (manuallyGenrated) {
				// get all EdEligibiliTyCargos for Manual Notice
				edEligibilityCargosDes = getEdEligibilityCargosForManualNotice(caseNumber, individualIds, generateDateString);

			} else {
				// get all EdEligibiliTyCargos for Auto Notice
				edEligibilityCargosDes = getEdEligibilityCargosForAutoNotice(coDaoServices, caseNumber, docId, coReqSeq, generateDateString);

			}

			// convert edge trace id's to string for query
			if (edEligibilityCargosDes != null && edEligibilityCargosDes.length > 0) {
				for (EdEligibilityCargo edEligibilityCargo : edEligibilityCargosDes) {
					allEdgTraceIdsAsList.add(String.valueOf(edEligibilityCargo.getEdgTraceId()));
				}
				edgTraceIdsStringForQuery = CorrespondenceServices.concatStringForQuery(allEdgTraceIdsAsList);
			}

			// get HasHmap of edgTraceId and NoticeReasons
			edgTraceIdNoticeReasonCodeMap = getEdgTraceIdNoticeReasonCodeMap(coDaoServices, caseNumber, edgTraceIdsStringForQuery);
			
			//Eliminating records with ED Reason code ED0020
            edEligibilityCargoList = new ArrayList<EdEligibilityCargo>();
            for (EdEligibilityCargo edEligibilityCargo : edEligibilityCargosDes) {
                String edgTraceId = String.valueOf(edEligibilityCargo.getEdgTraceId());
                ArrayList<String> noticeReasonCodesForEdgTraceId = edgTraceIdNoticeReasonCodeMap.get(edgTraceId);
                if(noticeReasonCodesForEdgTraceId== null ||(noticeReasonCodesForEdgTraceId != null && !noticeReasonCodesForEdgTraceId.contains("ED0020"))){
                    edEligibilityCargoList.add(edEligibilityCargo);
                }
            }           
            edEligibilityCargos = edEligibilityCargoList.toArray(new EdEligibilityCargo[0]);
            
			// get HasHmap of Coverage Code and Description
			coverageCodeDescriptionMap = getCoverageCodeDescriptionMap();
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while preprocessing data");
		}

		// Classify as "DENIED", "CLOSED", "APPROVED", "CHANGED"
		try {
			if (edEligibilityCargos != null && edEligibilityCargos.length > 0) {
				String recipientResponsibility = CoEdNoticeConstants.ELIGACA01RecipientLiability;
				// BEGIN: determine if the case was ever authorized prior to
				// this
				try {
					EdEligibilityCargo cargo = new EdEligibilityCargo();
					cargo.setCaseNum(Long.parseLong(caseNumber));
					//setting application date in cargo to determine authorizedBefore count ND-17590
					cargo.setApplicationDt(edEligibilityCargos[0].getApplicationDt());
					EdEligibilityCargo[] cargos = (EdEligibilityCargo[]) coDaoServices.findEdEligibilityForNCH001(cargo);
					if (cargos != null && cargos.length > 0) {
						authorizedBefore = true;
					}
				} catch (Exception e) {
					CoDebugger.debugInformation("Error while determining if caseNumebr ---> " + caseNumber + " was authorized ever before aside the current one");
					CoDebugger.debugException(e.getMessage(), e);
				}
				// END: determine if the case was ever authorized prior to this

				// getEdEligibility Records grouped by individuals in case
				indvIdEdEligibilityCargosMap = getIndvIdEdEligibilityCargosMap(edEligibilityCargos);
				for (String indvId : indvIdEdEligibilityCargosMap.keySet()) {

					indvIds.add(Long.valueOf(indvId).longValue());

					List<EdEligibilityCargo> dCargos = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> clCargos = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> aCargos = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> chCargos = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> tempCargos = new ArrayList<EdEligibilityCargo>();
					
					
					List<EdEligibilityCargo> dCargosSNAP = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> clCargosSNAP = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> aCargosSNAP = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> chCargosSNAP = new ArrayList<EdEligibilityCargo>();
					List<EdEligibilityCargo> tempCargosSNAP = new ArrayList<EdEligibilityCargo>();

					int deniedCount = 0;
					int closedCount = 0;
					int approvedCount = 0;
					int changedCount = 0;
					int tempCount = 0;
					int retroCount = 0;
					
					int deniedCountSNAP = 0;
					int closedCountSNAP = 0;
					int approvedCountSNAP= 0;
					int changedCountSNAP = 0;
					int tempCountSNAP = 0;
				

					// classify records corresponding to a specfic individual
					for (EdEligibilityCargo edEligibilityCargo : indvIdEdEligibilityCargosMap.get(indvId)) {
						if(edEligibilityCargo.getProgramCd().equalsIgnoreCase("MA")){
							displayHcc="Y";
						if (edEligibilityCargo != null && edEligibilityCargo.getCgStatusCd() != null) {
							if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_DENIED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
								// denied: main application
								deniedCount++;
								dCargos.add(edEligibilityCargo);
							} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_TERMINATED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0") && (edEligibilityCargo.getEligibilityBegDt() == null)) {
								// denied: same day approval-closure
								deniedCount++;
								dCargos.add(edEligibilityCargo);
							} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_TERMINATED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
								// closed: main application
								if (edEligibilityCargo.getPaymentEndDt() == null) {
									// closed: main application
									closedCount++;
									clCargos.add(edEligibilityCargo);
								} else {
									// closed or changed: main application
									tempCount++;
									tempCargos.add(edEligibilityCargo);
								}
							} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED)) && (edEligibilityCargo.getActivityType().equalsIgnoreCase(CoConstants.ACTIVITY_TYPE_INCREASED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
								// approved
								approvedCount++;
								aCargos.add(edEligibilityCargo);
							} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED))  && (edEligibilityCargo.getActivityType().equalsIgnoreCase("RE"))) {
								approvedCount++;
								aCargos.add(edEligibilityCargo);
							}else {
								if (edEligibilityCargo != null && !edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
									// RETRO
									
									 * FROM: Pandey, Chandra The retro
									 * application should be spate in the notice
									 * (this will be the change notice).
									 * 
									 * You can differentiate the main
									 * application with Retro with the Prior
									 * Medicaid Code in ED ELIGIBILITY. Main
									 * application will be P0 whereas the retro
									 * ones will be P1/P2/P3 respectively for
									 * the 3 months prior to the main
									 * application.
									 
									
									if(intakeFlag){
										if(edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED)){
											retroAppCheck = true;
											approvedCount++;
												aCargos.add(edEligibilityCargo);
											}
											else if(edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_DENIED)){
												deniedCount++;
												dCargos.add(edEligibilityCargo);
											}
									} else {
									retroCount++;
									changedCount++;
									chCargos.add(edEligibilityCargo);
									}
								} else {
									if (edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED) && !authorizedBefore) {
										// approved
										// if the case is re-authorized in case
										// change or add person mode on the very
										// first day, it should still be sent
										// out as approval and not change
										approvedCount++;
										aCargos.add(edEligibilityCargo);
									} else {
										// changed
										changedCount++;
										chCargos.add(edEligibilityCargo);
									}
								}
							}
						  }
						
					

					if (tempCount > 0) {
						if (closedCount > 0 && (tempCount + closedCount + retroCount) == indvIdEdEligibilityCargosMap.get(indvId).size()) {
							// closed: main application
							clCargos.addAll(tempCargos);
							closedCount = clCargos.size();
							tempCount = 0;
							tempCargos = new ArrayList<EdEligibilityCargo>();
						} else {
							// changed: main application
							chCargos.addAll(clCargos);
							chCargos.addAll(tempCargos);
							changedCount = chCargos.size();
							closedCount = 0;
							clCargos = new ArrayList<EdEligibilityCargo>();
							tempCount = 0;
							tempCargos = new ArrayList<EdEligibilityCargo>();
						}
					}

					if (deniedCount > 0) {
						if ((deniedCount + retroCount) == indvIdEdEligibilityCargosMap.get(indvId).size()) {
							displayDeniedTable = CoConstants.Y;
							if (!deniedIndvIdEdEligibilityCargosMap.keySet().contains(indvId)) {
								deniedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
							}
							deniedIndvIdEdEligibilityCargosMap.get(indvId).addAll(dCargos);
							deniedIndvIdEdEligibilityCargosMap.put(indvId, sortByBenefitStartDate(deniedIndvIdEdEligibilityCargosMap.get(indvId)));
							benefitDeniedMembers.setRecipientResponsibility(recipientResponsibility);
						} else {
							chCargos.addAll(dCargos);
							changedCount = chCargos.size();
						}
					}
					// check closure or change
					if (closedCount > 0) {
						if ((closedCount + retroCount) == indvIdEdEligibilityCargosMap.get(indvId).size()) {
							displayClosedTable = CoConstants.Y;
							if (!closedIndvIdEdEligibilityCargosMap.keySet().contains(indvId)) {
								closedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
							}
							closedIndvIdEdEligibilityCargosMap.get(indvId).addAll(clCargos);
							closedIndvIdEdEligibilityCargosMap.put(indvId, sortByBenefitStartDate(closedIndvIdEdEligibilityCargosMap.get(indvId)));
							benefitClosedMembers.setRecipientResponsibility(recipientResponsibility);
						} else {
							chCargos.addAll(clCargos);
							changedCount = chCargos.size();
						}
					}
					// check approval or change
					if (approvedCount > 0) {
						if ((approvedCount + retroCount) == indvIdEdEligibilityCargosMap.get(indvId).size() || retroAppCheck) {
							displayApprovedTable = CoConstants.Y;
							if (!approvedIndvIdEdEligibilityCargosMap.keySet().contains(indvId)) {
								approvedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
							}
							approvedIndvIdEdEligibilityCargosMap.get(indvId).addAll(aCargos);
							approvedIndvIdEdEligibilityCargosMap.put(indvId, sortByBenefitStartDate(approvedIndvIdEdEligibilityCargosMap.get(indvId)));
						} else {
							chCargos.addAll(aCargos);
							changedCount = chCargos.size();
						}
					}

					// check and sort changed cargos
					changedCount = chCargos.size();
					if (changedCount>0) {
						displayChangedTable = CoConstants.Y;
						if (!changedIndvIdEdEligibilityCargosMap.keySet().contains(indvId)) {
							changedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
						}
						changedIndvIdEdEligibilityCargosMap.get(indvId).addAll(chCargos);
						changedIndvIdEdEligibilityCargosMap.put(indvId, sortByBenefitStartDate(changedIndvIdEdEligibilityCargosMap.get(indvId)));
					}
				}else if(edEligibilityCargo.getProgramCd().equalsIgnoreCase("FS")){
						displaySNAP="Y";
					if (edEligibilityCargo != null && edEligibilityCargo.getCgStatusCd() != null) {
						if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_DENIED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
							// denied: main application
							deniedCountSNAP++;
							dCargosSNAP.add(edEligibilityCargo);
						} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_TERMINATED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0") && (edEligibilityCargo.getEligibilityBegDt() == null)) {
							// denied: same day approval-closure
							deniedCountSNAP++;
							dCargosSNAP.add(edEligibilityCargo);
						} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_TERMINATED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
							// closed: main application
							if (edEligibilityCargo.getPaymentEndDt() == null) {
								// closed: main application
								closedCountSNAP++;
								clCargosSNAP.add(edEligibilityCargo);
							} else {
								// closed or changed: main application
								tempCountSNAP++;
								tempCargosSNAP.add(edEligibilityCargo);
							}
						} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED)) && (edEligibilityCargo.getActivityType().equalsIgnoreCase(CoConstants.ACTIVITY_TYPE_INCREASED)) && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
							// approved
							approvedCountSNAP++;
							aCargosSNAP.add(edEligibilityCargo);
						} else if ((edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED))  && (edEligibilityCargo.getActivityType().equalsIgnoreCase("RE"))) {
							approvedCountSNAP++;
							aCargosSNAP.add(edEligibilityCargo);
						}else {
							if (edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED) && !authorizedBefore) {
								// approved
								// if the case is re-authorized in case
								// change or add person mode on the very
								// first day, it should still be sent
								// out as approval and not change
								approvedCount++;
								aCargos.add(edEligibilityCargo);
							} else {
								// changed
								changedCountSNAP++;
								chCargosSNAP.add(edEligibilityCargo);
							}
					
					
				}
						if (tempCount > 0) {
							if (closedCount > 0 && (tempCount + closedCount) == indvIdEdEligibilityCargosMap.get(indvId).size()) {
								// closed: main application
								clCargos.addAll(tempCargos);
								closedCount = clCargos.size();
								tempCount = 0;
								tempCargos = new ArrayList<EdEligibilityCargo>();
							} else {
								// changed: main application
								chCargos.addAll(clCargos);
								chCargos.addAll(tempCargos);
								changedCount = chCargos.size();
								closedCount = 0;
								clCargos = new ArrayList<EdEligibilityCargo>();
								tempCount = 0;
								tempCargos = new ArrayList<EdEligibilityCargo>();
							}
						}

						if (deniedCountSNAP > 0) {
								if (!deniedIndvIdEdEligibilityCargosSNAPMap.keySet().contains(indvId)) {
									deniedIndvIdEdEligibilityCargosSNAPMap.put(indvId, new ArrayList<EdEligibilityCargo>());
								}
								deniedIndvIdEdEligibilityCargosSNAPMap.get(indvId).addAll(dCargos);
								deniedIndvIdEdEligibilityCargosSNAPMap.put(indvId, sortByBenefitStartDate(deniedIndvIdEdEligibilityCargosSNAPMap.get(indvId)));
								
							} 
						
						// check closure or change
						if (closedCountSNAP > 0) {
								displayClosedTable = CoConstants.Y;
								if (!closedIndvIdEdEligibilityCargosMap.keySet().contains(indvId)) {
									closedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
								}
								closedIndvIdEdEligibilityCargosMap.get(indvId).addAll(clCargos);
								closedIndvIdEdEligibilityCargosMap.put(indvId, sortByBenefitStartDate(closedIndvIdEdEligibilityCargosMap.get(indvId)));
								benefitClosedMembers.setRecipientResponsibility(recipientResponsibility);
							} 
					
						// check approval or change
						if (approvedCountSNAP > 0) {
								if (!approvedIndvIdEdEligibilityCargosSNAPMap.keySet().contains(indvId)) {
									approvedIndvIdEdEligibilityCargosSNAPMap.put(indvId, new ArrayList<EdEligibilityCargo>());
								}
								approvedIndvIdEdEligibilityCargosSNAPMap.get(indvId).addAll(aCargos);
								approvedIndvIdEdEligibilityCargosSNAPMap.put(indvId, sortByBenefitStartDate(approvedIndvIdEdEligibilityCargosMap.get(indvId)));
							}
						

						// check and sort changed cargos
						changedCountSNAP = chCargos.size();
						if (changedCountSNAP>0) {
							if (!changedIndvIdEdEligibilityCargosMapSNAP.keySet().contains(indvId)) {
								changedIndvIdEdEligibilityCargosMap.put(indvId, new ArrayList<EdEligibilityCargo>());
							}
							changedIndvIdEdEligibilityCargosMapSNAP.get(indvId).addAll(chCargos);
							changedIndvIdEdEligibilityCargosMapSNAP.put(indvId, sortByBenefitStartDate(changedIndvIdEdEligibilityCargosMap.get(indvId)));
						}
					}
				}	
			 }
		  }
		}
	} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while classifying records as Denial, Closure, Approval and Change");
			CoDebugger.debugException(e.getMessage(), e);
		}

		// getindvIdNameMap
		try {
			indvIdNameMap = getIndvIdNameMap(coDaoServices, indvIds);
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while preparing indvIdNameMap");
			CoDebugger.debugException(e.getMessage(), e);
		}
		
		if(displaySNAP.equalsIgnoreCase("Y")){
			
		}
		
		// DENIED MEMEBER TABLE DATA
		try {
			for (String indvId : deniedIndvIdEdEligibilityCargosMap.keySet()) {
				
				benefitDeniedMemberInfo = new BenefitDeniedMemberInfo();
				
				String individualId = indvId;
				String name = indvIdNameMap.get(individualId);
				benefitDeniedMemberInfo.setName(name);
				
				String previousRowText = "";
				benefitDenialInfos = new ArrayList<BenefitDenialInfo>();
				for (EdEligibilityCargo edEligibilityCargo : deniedIndvIdEdEligibilityCargosMap.get(indvId)) {
					if (benefitDeniedMemberInfo.getDateSubmitted() == null) {
						benefitDeniedMemberInfo.setDateSubmitted(getDateAsString(edEligibilityCargo.getApplicationDt()));
					}

					String edgTraceId = String.valueOf(edEligibilityCargo.getEdgTraceId());
					ArrayList<String> noticeReasonCodesForEdgTraceId = edgTraceIdNoticeReasonCodeMap.get(edgTraceId);
					//ND-15425: BEGIN #1 Remove As Of Date with benefit span
					//String asOfDate = null;
					String benefitSpan = null;
					//ND-15425: END #1
					
					
					String coverageCode =CoConstants.SPACE;
					String coverageCategory =CoConstants.SPACE;
					if (edEligibilityCargo.getCoverageGroup() != null) {
						coverageCode = edEligibilityCargo.getCoverageGroup();
						coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
					}
					
					
					if (noticeReasonCodesForEdgTraceId != null && noticeReasonCodesForEdgTraceId.size() > 0) {
						String currentRowText = "";
						String prefix = "";
						List<BenefitDenialInfo> tempBenefitDenialInfos = new ArrayList<BenefitDenialInfo>();
						for (String noticeReasonCode : noticeReasonCodesForEdgTraceId) {
							if (noticeReasonCodeRefDataMap == null) {
								noticeReasonCodeRefDataMap = getNoticeReasonCodeRefDataMap(edgTraceIdNoticeReasonCodeMap);
							}
							
							//r2
							eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
							explanationText=new ExplanationOfBenefits();
							benefitDenialInfo = new BenefitDenialInfo();
							benefitDenialInfo.setAsOfDate(CoConstants.SPACE);
							String denialReasonText = CoConstants.SPACE;
							String moreInfoText = CoConstants.SPACE;
							String headerValue  = CoConstants.SPACE;;
							//ND-15425: BEGIN #1 Remove As Of Date with benefit span
//							if (asOfDate == null) {
//								asOfDate = getDateAsString(edEligibilityCargo.getApplicationDt());
//								benefitDenialInfo.setAsOfDate(asOfDate);
//							}
							if (benefitSpan == null) {
								String periodStartDate = getDateAsString(edEligibilityCargo.getBenefitStartDt());
								String periodEndDate = getDateAsString(edEligibilityCargo.getBenefitEndDt());
								//benefitSpan = periodStartDate + " - " + periodEndDate;
								benefitDenialInfo.setAsOfDate(benefitSpan);
								if(null!=periodEndDate && periodEndDate.length()>0){
									 benefitSpan = periodStartDate + " - " + periodEndDate;
								}else{
									 benefitSpan = "As of "+periodStartDate ;
								}
								
							}
							//ND-15425: END #1
							
							//R2
							eligibilityHCCTable.setCoverageCategory(coverageCategory);
							eligibilityHCCTable.setBenefitSpan(benefitSpan);
							eligibilityHCCTable.setStatus("Denied");
							eligibilityHCCTable.setPerson(name);
							eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
							
							RefTableData refData = noticeReasonCodeRefDataMap.get(noticeReasonCode);
							String displayFilter = "";
							if (refData != null) {
								ArrayList<NoticeOfEligibilityHCCTable> deniedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								displayFilter = (String) refData.getRefrTableAddiData().get("DISPLAYFILTER");
								if (displayFilter != null && displayFilter.equalsIgnoreCase("Y")) {
									String denialReasonCode = (String) refData.getRefrTableAddiData().get("NOTICE");
									String moreInfoCode = (String) refData.getRefrTableAddiData().get("REASONDESC");
									denialReasonText = getTextValue(denialReasonCode, edEligibilityCargo);
									moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
									String edCode[]=moreInfoCode.split("-");
								    if(edCode.length>0){
									eligibilityHCCTable.setEdCode(moreInfoCode);
								    }
								    else{
								    	eligibilityHCCTable.setEdCode(moreInfoCode);
								    }

									explanationText.setExplanationValue(moreInfoText);
									
									if(denialReasonText.length()>0){
									explanationText.setExplanationHeader(denialReasonText);
									}else{
									explanationText.setExplanationHeader("SAMPLE HEADER");
									}
										if(!infoMap.containsKey(moreInfoCode)){
										infoMap.put(moreInfoCode, explanationText);
										}
										if(finalHccMap.isEmpty()){
											finalHccMap.put(individualId, deniedList);
										}else if(!finalHccMap.containsKey(individualId)){
											finalHccMap.put(individualId,deniedList);
										}else{
											finalHccMap.get(individualId).add(eligibilityHCCTable);
										}
										eligibilityHCCTables.add(eligibilityHCCTable);
								}
							}
							//R2 commented
							if(denialReasonText != null && denialReasonText.length()>0 && !denialReasonText.equalsIgnoreCase(CoConstants.SPACE)){
								currentRowText = currentRowText + prefix + denialReasonText;
								prefix = " ";
							}
							//R2 modified to show same as above 
							if(moreInfoText != null && moreInfoText.length()>0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)){
								currentRowText = currentRowText + prefix + moreInfoText;
								prefix = " ";
								currentRowText =moreInfoText;
							}
							benefitDenialInfo.setDenialReason(denialReasonText);
							
							benefitDenialInfo.setMoreInfo(moreInfoText);
							//DO NOT add benefitDenialInfo if both denialReasonText and moreInfoText are blank
							if((denialReasonText!=null && denialReasonText.trim().length()>0) || (moreInfoText!=null && moreInfoText.trim().length()>0)){
								tempBenefitDenialInfos.add(benefitDenialInfo);
							}							
							//benefitDenialInfos.add(benefitDenialInfo);
						}
//						if(currentRowText == null || (currentRowText != null && currentRowText.length()==0)){
//							if(tempBenefitDenialInfos.size()==0 && previousRowText != null && previousRowText.length()>0 ){
//								currentRowText = previousRowText;
//							}
//						}
						
						if(previousRowText != null && currentRowText != null && previousRowText.length()>0 && !previousRowText.equalsIgnoreCase(CoConstants.SPACE) && previousRowText.equalsIgnoreCase(currentRowText)){
							//ND-14309: BEGIN
							//if denial resons in same then show just one row.
//							for(BenefitDenialInfo denialInfo: tempBenefitDenialInfos){
//								denialInfo.setDenialReason(CoConstants.SAME_AS_ABOVE);
//								denialInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
//								benefitDenialInfos.add(denialInfo);
//							}
							//ND-14309: BEGIN
							
							//ND-15425: BEGIN #1 Remove As Of Date with benefit span Overrides ND-14309
							for(BenefitDenialInfo denialInfo: tempBenefitDenialInfos){
								if(denialInfo.getDenialReason() != null && denialInfo.getDenialReason().length()>0 && !denialInfo.getDenialReason().equalsIgnoreCase(CoConstants.SPACE)){
									denialInfo.setDenialReason(CoConstants.SAME_AS_ABOVE);
								}
								if(denialInfo.getMoreInfo() != null && denialInfo.getMoreInfo().length()>0 && !denialInfo.getMoreInfo().equalsIgnoreCase(CoConstants.SPACE)){
									denialInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
								}								
								benefitDenialInfos.add(denialInfo);
								break;
							}
							//ND-15425: END #1 Remove As Of Date with benefit span Overrides ND-14309
						}else{//(previousRowText == null || previousRowText.length() ==0){
							benefitDenialInfos.addAll(tempBenefitDenialInfos);	
						}
						if(currentRowText != null && currentRowText.trim().length()>0 && !currentRowText.equalsIgnoreCase(CoConstants.SPACE)){
							previousRowText = currentRowText;
						}
					}
					
				}
				benefitDeniedMemberInfo.setBenefitDenialInfo(benefitDenialInfos);
				
				
				//DO NOT add benefitDeniedMemberInfo if benefitDenialInfos is empty
				if (benefitDeniedMemberInfo.getBenefitDenialInfo().size() > 0) {
					deniedMembers.add(benefitDeniedMemberInfo);
				}
				
			}
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while populating Denial Table");
			CoDebugger.debugException(e.getMessage(), e);
		}

		// CLOSED MEMEBER TABLE DATA
		try {
			for (String indvId : closedIndvIdEdEligibilityCargosMap.keySet()) {
				
				benefitClosedMemberInfo = new BenefitClosedMemberInfo();
				String individualId = indvId;
				String name = indvIdNameMap.get(individualId);
				benefitClosedMemberInfo.setName(name);

				benefitClosureInfos = new ArrayList<BenefitClosureInfo>();
				String previousRowText = "";
				for (EdEligibilityCargo edEligibilityCargo : closedIndvIdEdEligibilityCargosMap.get(indvId)) {
					if (benefitClosedMemberInfo.getClosureDate() == null) {
						benefitClosedMemberInfo.setClosureDate(getDateAsString(edEligibilityCargo.getEligibilityEndDt()));
					}
					
					
					String edgTraceId = String.valueOf(edEligibilityCargo.getEdgTraceId());
					ArrayList<String> noticeReasonCodesForEdgTraceId = edgTraceIdNoticeReasonCodeMap.get(edgTraceId);
					String benefitSpan = null;
					if (noticeReasonCodesForEdgTraceId != null && noticeReasonCodesForEdgTraceId.size() > 0) {
						String currentRowText = "";
						String prefix = "";
						List<BenefitClosureInfo> tempClosureInfos = new ArrayList<BenefitClosureInfo>();
						for (String noticeReasonCode : noticeReasonCodesForEdgTraceId) {
							if (noticeReasonCodeRefDataMap == null) {
								noticeReasonCodeRefDataMap = getNoticeReasonCodeRefDataMap(edgTraceIdNoticeReasonCodeMap);
							}
							
							//r2
							eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
							explanationText=new ExplanationOfBenefits();
							String coverageCode =CoConstants.SPACE;
							String coverageCategory =CoConstants.SPACE;
							
							if (edEligibilityCargo.getCoverageGroup() != null) {
								coverageCode = edEligibilityCargo.getCoverageGroup();
								coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
							}
							
							
							
							benefitClosureInfo = new BenefitClosureInfo();
							benefitClosureInfo.setBenefitSpan(CoConstants.SPACE);
							String closureReasonText = CoConstants.SPACE;
							String moreInfoText = CoConstants.SPACE;

							if (benefitSpan == null) {
								String periodStartDate = getDateAsString(edEligibilityCargo.getBenefitStartDt());
								String periodEndDate = getDateAsString(edEligibilityCargo.getBenefitEndDt());
								//benefitSpan = periodStartDate + " - " + periodEndDate;
								benefitDenialInfo.setAsOfDate(benefitSpan);
								if(null!=periodEndDate && periodEndDate.length()>0){
									 benefitSpan = periodStartDate + " - " + periodEndDate;
								}else{
									 benefitSpan = "As of "+periodStartDate ;
								}
								
							}
							
							//R2
							eligibilityHCCTable.setCoverageCategory(coverageCategory);
							eligibilityHCCTable.setBenefitSpan(benefitSpan);
							eligibilityHCCTable.setStatus("Closed");
							eligibilityHCCTable.setPerson(name);
							eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());

							RefTableData refData = noticeReasonCodeRefDataMap.get(noticeReasonCode);
							String displayFilter = "";
							if (refData != null) {
								ArrayList<NoticeOfEligibilityHCCTable> closedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								displayFilter = (String) refData.getRefrTableAddiData().get("DISPLAYFILTER");
								if (displayFilter != null && displayFilter.equalsIgnoreCase("Y")) {
									String closureReasonCode = (String) refData.getRefrTableAddiData().get("NOTICE");
									String moreInfoCode = (String) refData.getRefrTableAddiData().get("REASONDESC");
									closureReasonText = getTextValue(closureReasonCode, edEligibilityCargo);
									moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
									
								   // String edCode[]=moreInfoCode.split("-");
								   // if(edCode.length>0){
									//eligibilityHCCTable.setEdCode(edCode[0]);
								    }
								    else{
								    	eligibilityHCCTable.setEdCode(moreInfoCode);
								    }
									explanationText.setExplanationValue(moreInfoText);
									
									if(closureReasonText.length()>0){
									explanationText.setExplanationHeader(closureReasonText);
									}else{
										explanationText.setExplanationHeader("SAMPLE HEADER");
									}
										if(!infoMap.containsKey(moreInfoCode)){
										infoMap.put(moreInfoCode, explanationText);
										}
										if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
											finalHccMap.put(individualId, closedList);
										}
											finalHccMap.get(individualId).add(eligibilityHCCTable);
										
										eligibilityHCCTables.add(eligibilityHCCTable);
									
									
									
								}
							}
							if(closureReasonText != null && closureReasonText.length()>0 && !closureReasonText.equalsIgnoreCase(CoConstants.SPACE)){
								currentRowText = currentRowText + prefix + closureReasonText;
								prefix = " ";
							}
							if(moreInfoText != null && moreInfoText.length()>0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)){
								currentRowText = currentRowText + prefix + moreInfoText;
								prefix = " ";
							}
							benefitClosureInfo.setClosureReason(closureReasonText);
							benefitClosureInfo.setMoreInfo(moreInfoText);
							//DO NOT add benefitClosureInfo if both closureReasonText and moreInfoText are blank
							if((closureReasonText!=null && closureReasonText.trim().length()>0) || (moreInfoText!=null && moreInfoText.trim().length()>0)){
								tempClosureInfos.add(benefitClosureInfo);
							}							
						}
//						if(currentRowText == null || (currentRowText != null && currentRowText.length()==0)){
//							if(tempClosureInfos.size()==0 && previousRowText != null && previousRowText.length()>0 ){
//								currentRowText = previousRowText;
//							}
//						}
						if(previousRowText != null && currentRowText != null && previousRowText.length()>0 && !previousRowText.equalsIgnoreCase(CoConstants.SPACE) && previousRowText.equalsIgnoreCase(currentRowText)){
							//ND-15082: BEGIN
							for(BenefitClosureInfo closureInfo: tempClosureInfos){
								if(closureInfo.getClosureReason() != null && closureInfo.getClosureReason().length()>0 && !closureInfo.getClosureReason().equalsIgnoreCase(CoConstants.SPACE)){
									closureInfo.setClosureReason(CoConstants.SAME_AS_ABOVE);
								}
								if(closureInfo.getMoreInfo() != null && closureInfo.getMoreInfo().length()>0 && !closureInfo.getMoreInfo().equalsIgnoreCase(CoConstants.SPACE)){
									closureInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
								}								
								benefitClosureInfos.add(closureInfo);
								break;
							}						
//							for(BenefitClosureInfo closureInfo: tempClosureInfos){
//								closureInfo.setClosureReason(CoConstants.SAME_AS_ABOVE);
//								closureInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
//								benefitClosureInfos.add(closureInfo);
//							}
//							previousRowText = currentRowText;
							//ND-15082: END
						}else{
							benefitClosureInfos.addAll(tempClosureInfos);	
						}
						if(currentRowText != null && currentRowText.trim().length()>0 && !currentRowText.equalsIgnoreCase(CoConstants.SPACE)){
							previousRowText = currentRowText;
						}
					}
				}
				benefitClosedMemberInfo.setBenefitClosureInfo(benefitClosureInfos);
		
				//DO NOT add benefitClosedMemberInfo if benefitClosureInfos is empty
				if (benefitClosedMemberInfo.getBenefitClosureInfo().size() > 0) {
					closedMembers.add(benefitClosedMemberInfo);
				}
			}
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while populating Closure Table");
			CoDebugger.debugException(e.getMessage(), e);
		}

		// APPROVED MEMEBER TABLE DATA
		try {
			//added for R2: disability check
			ArrayList<String>approvedIndvs=new ArrayList<String>();
			for(String id : approvedIndvIdEdEligibilityCargosMap.keySet()){
				approvedIndvs.add(id);
			}
			HashMap<String, DcDisabilityCargo>disabilityIndvs=null;
			if(null!=approvedIndvs && approvedIndvs.size()>0){
				disabilityIndvs=noeMedicaid.getIndvMeetsDisability(approvedIndvs);
			}
			for (String indvId : approvedIndvIdEdEligibilityCargosMap.keySet()) {
				benefitApprovedMemberInfo = new BenefitApprovedMemberInfo();
				String individualId = indvId;
				String name = indvIdNameMap.get(individualId);
				benefitApprovedMemberInfo.setName(name);
				String currentRowText = "";
				
				String previousRowText = "";
				benefitApprovalInfos = new ArrayList<BenefitApprovalInfo>();
				for (EdEligibilityCargo edEligibilityCargo : approvedIndvIdEdEligibilityCargosMap.get(indvId)) {
					String currentRowText = "";
					String prefix = "";
					String tempValue=null;
					List<BenefitApprovalInfo> tempApprovalInfos = new ArrayList<BenefitApprovalInfo>();
					
					//r2 not requried
					if (benefitApprovedMemberInfo.getApplicationDate() == null) {
						benefitApprovedMemberInfo.setApplicationDate(getDateAsString(edEligibilityCargo.getApplicationDt()));
					}

					benefitApprovalInfo = new BenefitApprovalInfo();
					//r2
					eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
					explanationText=new ExplanationOfBenefits();

					String periodStartDate = getDateAsString(edEligibilityCargo.getBenefitStartDt());
					String periodEndDate = getDateAsString(edEligibilityCargo.getBenefitEndDt());
					String benefitSpan=null;
					//r2
					if(null!=periodEndDate && periodEndDate.length()>0){
						 benefitSpan = periodStartDate + " - " + periodEndDate;
					}else{
						 benefitSpan = "As of "+periodStartDate ;
					}
					
					String coverageCategory = CoConstants.SPACE;
					String coverageCode = CoConstants.EMPTY_STRING;
					String moreInfoCode = CoConstants.EMPTY_STRING;
					String moreInfoHeaderCode=CoConstants.EMPTY_STRING;
					String moreInfoText = CoConstants.EMPTY_STRING;
					String headerValue = CoConstants.EMPTY_STRING;
					String toa = edEligibilityCargo.getTypeOfAssistanceCd();
					//r2
					//eligibilityHCCTable.setNoeBenefitSpan(benefitSpan);
					
					if (edEligibilityCargo.getCoverageGroup() != null) {
						coverageCode = edEligibilityCargo.getCoverageGroup();
						coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
					}

					// ND-15425: #2 BEGIN Do not show row if coverage category
					// is invalid
					if (coverageCategory != null && coverageCategory.length() > 0 && !coverageCategory.equalsIgnoreCase(CoConstants.SPACE)) {
						if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) {
							displayNoticeAttachment = CoConstants.Y;
						}

						// ND-14313: BEGIN
						boolean emergencyServices = false;
						String coe = edEligibilityCargo.getCoe();
						//R2 coe code for emergencyServices
						if (toa != null && toa.equalsIgnoreCase("MA88") || (coe!=null && coe.equalsIgnoreCase("M081"))) {
							emergencyServices = true;
						}
						boolean medicallyFrailInstitutionalized = false;
						boolean medicallyFrailNotInstitutionalized = false;
						boolean transitionalIncome = false;
						boolean transitionalSpousal = false;
						//R2
						boolean refugee=false;
						
						
						if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MA) && coe != null){
							if( coe.equalsIgnoreCase("M060") || coe.equalsIgnoreCase("M065") ) {
								medicallyFrailInstitutionalized = true;
							}else if( coe.equalsIgnoreCase("M058") || coe.equalsIgnoreCase("M059") ) {
								medicallyFrailNotInstitutionalized = true;
							}else if( coe.equalsIgnoreCase("M086") || coe.equalsIgnoreCase("M087") ) {
								transitionalIncome = true;
							}else if( coe.equalsIgnoreCase("M061") || coe.equalsIgnoreCase("M088") ) {
								transitionalSpousal = true;
							}else if( coe.equalsIgnoreCase("06R")) {
								refugee = true;
							}									
						}
						//ND-15309: END
						
						if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MA) && !emergencyServices) { // ND-14313:																												// END
							//ND-15309: BEGIN
							if(medicallyFrailInstitutionalized){
								moreInfoCode = "ACA013501PassMedicallyFrailInstitutionalized";
							}else if(medicallyFrailNotInstitutionalized){
								moreInfoCode = "ACA013502PassMedicallyFrailNotInstitutionalized";
							}else{
								moreInfoCode = "ACA.01.27 – Pass Medicaid";
							}
							//ND-15309: END
						} else if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) {
							moreInfoCode = "ACA.01.36 – Pass Medicaid Expansion";
							moreInfoHeaderCode="ACA0132IncomeHeader";
						} else if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_HS)) {
							
							moreInfoCode = "ACA.01.28 – Healthy Steps";
							moreInfoHeaderCode="ACA0228HealthyStepsHeader";
							String childrenNames=noeMedicaid.getCoPayIndvs(indvIdEdEligibilityCargosMap, allEdgTraceIdsAsList);
							if(childrenNames.length()>0)
							{
								tempValue=edEligibilityCargo.getBenefitStatus();
								edEligibilityCargo.setBenefitStatus(childrenNames);
							}
							
						}else  if(emergencyServices){ //R2
							moreInfoCode = "ACA0133EmergencyServices";
							moreInfoHeaderCode="ACA0133EmergencyServicesHeader";
						}
						else {
							// No other Category possible
						}
						moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
						headerValue=getTextValue(moreInfoHeaderCode, edEligibilityCargo);
						if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_HS)) {
							edEligibilityCargo.setBenefitStatus(tempValue);
						}
						if (moreInfoText.length() > 0) {
							ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
							benefitApprovalInfo = new BenefitApprovalInfo();
							benefitApprovalInfo.setBenefitSpan(benefitSpan);
							benefitApprovalInfo.setCoverageCategory(coverageCategory);
							benefitApprovalInfo.setMoreInfo(moreInfoText);
							tempApprovalInfos.add(benefitApprovalInfo);
							//r2
							eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
							explanationText=new ExplanationOfBenefits();
							eligibilityHCCTable.setPerson(name);
							eligibilityHCCTable.setBenefitSpan(benefitSpan);
							eligibilityHCCTable.setStatus("Approved");
							eligibilityHCCTable.setCoverageCategory(coverageCategory);
							eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
							String edCode[]=moreInfoCode.split("-");
						    if(edCode.length>0){
							eligibilityHCCTable.setEdCode(edCode[0]);
						    }
						    else{
						    	eligibilityHCCTable.setEdCode(moreInfoCode);
						    }
							
							
							
							
							explanationText.setExplanationValue(moreInfoText);
							if(headerValue.length()>0){
							explanationText.setExplanationHeader(headerValue);
							}else{
							explanationText.setExplanationHeader("SAMPLE HEADER");
							}
							approvedList.add(eligibilityHCCTable);
							
								
								
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
							
							benefitSpan = CoConstants.SPACE;
							coverageCategory = CoConstants.SPACE;
							if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
								currentRowText = currentRowText + prefix + moreInfoText;
								prefix = " ";
								currentRowText=moreInfoText;
							}
							eligibilityHCCTables.add(eligibilityHCCTable);
							if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
								finalHccMap.put(individualId, approvedList);
							}
								finalHccMap.get(individualId).add(eligibilityHCCTable);
							
							//List<NoticeOfEligibilityHCCTable>a=finalHccMap.get(individualId);
							
						}
						
						//ND-15560: BEGIN						
						if (edEligibilityCargo.getExpandedPraSw() == CoConstants.CHAR_Y)  {
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							moreInfoCode = "ACA0137FailMedicallyFrail";
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							if (moreInfoText.length() > 0) {
								benefitApprovalInfo = new BenefitApprovalInfo();
								benefitApprovalInfo.setBenefitSpan(benefitSpan);
								tempApprovalInfos.add(benefitApprovalInfo);
								
								
								//r2
								eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
								explanationText=new ExplanationOfBenefits();
								eligibilityHCCTable.setPerson(name);
								eligibilityHCCTable.setBenefitSpan(benefitSpan);
								eligibilityHCCTable.setCoverageCategory(coverageCategory);
								eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
								eligibilityHCCTable.setStatus("Approved");
								String edCode[]=moreInfoCode.split("-");
							    if(edCode.length>0){
								eligibilityHCCTable.setEdCode(edCode[0]);
							    }
							    else{
							    	eligibilityHCCTable.setEdCode(moreInfoCode);
							    }
								eligibilityHCCTables.add(eligibilityHCCTable);
								explanationText.setExplanationValue(moreInfoText);
								explanationText.setExplanationHeader("Sample Header");
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
								}
								
							}
						}
						moreInfoCode = CoConstants.EMPTY_STRING;
						moreInfoText = CoConstants.EMPTY_STRING;
						if(transitionalIncome){
							moreInfoCode = "ACA0132Income";
							moreInfoHeaderCode="ACA0132IncomeHeader";
							
						}else if(transitionalSpousal){
							moreInfoCode = "ACA0131SpousalSupport";
							moreInfoCode="ACA0131SpousalSupportHeader";
						}else if(refugee){
							moreInfoCode="ACARefugee";
							moreInfoHeaderCode="ACARefugeeHeader";
						}
						moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
						headerValue =  getTextValue(moreInfoHeaderCode, edEligibilityCargo);
						if (moreInfoText.length() > 0) {
							ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
							benefitApprovalInfo = new BenefitApprovalInfo();
							benefitApprovalInfo.setBenefitSpan(benefitSpan);
							benefitApprovalInfo.setCoverageCategory(coverageCategory);
							benefitApprovalInfo.setMoreInfo(moreInfoText);
							tempApprovalInfos.add(benefitApprovalInfo);
							//r2
							eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
							explanationText=new ExplanationOfBenefits();
							eligibilityHCCTable.setPerson(name);
							eligibilityHCCTable.setBenefitSpan(benefitSpan);
							eligibilityHCCTable.setCoverageCategory(coverageCategory);
							eligibilityHCCTable.setStatus("Approved");
							eligibilityHCCTables.add(eligibilityHCCTable);
							eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
							String edCode[]=moreInfoCode.split("-");
						    if(edCode.length>0){
							eligibilityHCCTable.setEdCode(edCode[0]);
						    }
						    else{
						    	eligibilityHCCTable.setEdCode(moreInfoCode);
						    }
							explanationText.setExplanationValue(moreInfoText);
							if(headerValue.length()>0){
							explanationText.setExplanationHeader(headerValue);
							}else{
							explanationText.setExplanationHeader("SAMPLE HEADER");
							}
							if(!infoMap.containsKey(moreInfoCode)){
							infoMap.put(moreInfoCode, explanationText);
							}
							if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
								finalHccMap.put(individualId, approvedList);
							}
								finalHccMap.get(individualId).add(eligibilityHCCTable);
							
							benefitSpan = CoConstants.SPACE;
							coverageCategory = CoConstants.SPACE;
							if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
								currentRowText = currentRowText + prefix + moreInfoText;
								prefix = " ";
							}
							
						}
						//ND-15560: END
						
						if (toa != null) {
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							moreInfoHeaderCode=CoConstants.EMPTY_STRING;
//ND-16704					if (toa.equalsIgnoreCase("MA20") && edEligibilityCargo.getCepSw() != CoConstants.YES_CHAR) {
							if (toa.equalsIgnoreCase("MA20") && edEligibilityCargo.getCepSw() != CoConstants.YES_CHAR && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
								moreInfoCode = "ACA.01.30 – Extended";
								moreInfoHeaderCode="ACA0130ExtendedHeader";
								tempValue=edEligibilityCargo.getBenefitStatus();
								edEligibilityCargo.setBenefitStatus(asOfDate);
								
							} else if (!transitionalSpousal && toa.equalsIgnoreCase("MA80")) {
								moreInfoCode = "ACA.01.31 – Spousal Support";
							} else if (!transitionalIncome && toa.equalsIgnoreCase("MA79")) {
								moreInfoCode = "ACA.01.32 – Income";
							} else if (toa.equalsIgnoreCase("MA88")) {
								moreInfoCode = "ACA.01.33 – Emergency Services";
							}else if (toa.equalsIgnoreCase("CW10")) {
								moreInfoCode = "ChildrenDisabilitiesCoverage ";
								moreInfoHeaderCode="ChildrenDisabilitiesCoverageHeader";
							}else if(toa.equalsIgnoreCase("WWD")){
								moreInfoCode = "WorkersDisabiltyCoverageValue";
								moreInfoHeaderCode="WorkersDisabiltyCoverageHeader";
							}else if(toa.equalsIgnoreCase("QMB") || toa.equalsIgnoreCase("SLMB")||toa.equalsIgnoreCase("QI-1")){
								if (!mspApprovedMap.keySet().contains(indvId)) {
									mspApprovedMap.put(indvId, new ArrayList<EdEligibilityCargo>());
								}
								mspApprovedMap.get(indvId).add(edEligibilityCargo);
								
							}
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							headerValue=getTextValue(moreInfoHeaderCode, edEligibilityCargo);
							if(moreInfoCode.equalsIgnoreCase("ACA.01.30 – Extended")){
								edEligibilityCargo.setBenefitStatus(tempValue);
								tempValue=null;
							}
							if (moreInfoText.length() > 0) {
								ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								benefitApprovalInfo = new BenefitApprovalInfo();
								benefitApprovalInfo.setBenefitSpan(benefitSpan);
								benefitApprovalInfo.setCoverageCategory(coverageCategory);
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								benefitApprovalInfo.setMoreInfo(moreInfoText);
								tempApprovalInfos.add(benefitApprovalInfo);
								//r2
								eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
								explanationText=new ExplanationOfBenefits();
								eligibilityHCCTable.setPerson(name);
								eligibilityHCCTable.setBenefitSpan(benefitSpan);
								eligibilityHCCTable.setCoverageCategory(coverageCategory);
								eligibilityHCCTable.setStatus("Approved");
								eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
	
								String edCode[]=moreInfoCode.split("-");
							    if(edCode.length>0){
								eligibilityHCCTable.setEdCode(edCode[0]);
							    }
							    else{
							    	eligibilityHCCTable.setEdCode(moreInfoCode);
							    }
								eligibilityHCCTables.add(eligibilityHCCTable);
								
									if(headerValue.length()>0){
									explanationText.setExplanationHeader(headerValue);
									}else{
									explanationText.setExplanationHeader("SAMPLE HEADER");
									}
								explanationText.setExplanationValue(moreInfoText);
								
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
								if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
									finalHccMap.put(individualId, approvedList);
								}
									finalHccMap.get(individualId).add(eligibilityHCCTable);
								
								
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
									currentRowText =  moreInfoText;

								}
							}
						}
						
						// ND-15318: BEGIN
						if (edEligibilityCargo.getCopayAmt() == 0 && !coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) { // ND-15318:END
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							moreInfoCode = "ACA.01.29 – No CoPay";
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);							
							if (moreInfoText.length() > 0) {
								ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								benefitApprovalInfo = new BenefitApprovalInfo();
								benefitApprovalInfo.setBenefitSpan(benefitSpan);
								benefitApprovalInfo.setCoverageCategory(coverageCategory);
								benefitApprovalInfo.setMoreInfo(moreInfoText);
								tempApprovalInfos.add(benefitApprovalInfo);
								
								//r2
								eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
								explanationText=new ExplanationOfBenefits();
								eligibilityHCCTable.setPerson(name);
								eligibilityHCCTable.setBenefitSpan(benefitSpan);
								eligibilityHCCTable.setCoverageCategory(coverageCategory);
								eligibilityHCCTable.setStatus("Approved");
								
								eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
								String edCode[]=moreInfoCode.split("-");
							    if(edCode.length>0){
								eligibilityHCCTable.setEdCode(edCode[0]);
							    }
							    else{
							    	eligibilityHCCTable.setEdCode(moreInfoCode);
							    }
								eligibilityHCCTables.add(eligibilityHCCTable);
								explanationText.setExplanationValue(moreInfoText);
								explanationText.setExplanationHeader("Sample Header");
								
								
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
								if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
									finalHccMap.put(individualId, approvedList);
								}
									finalHccMap.get(individualId).add(eligibilityHCCTable);
								
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
								}
							}
						}
						//R2 :reopened case
						if((edEligibilityCargo.getActivityType().equalsIgnoreCase("RE") ||edEligibilityCargo.getActivityType().equalsIgnoreCase("PR") ) && edEligibilityCargo.getCgStatusCd().equalsIgnoreCase("AP") ){
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							headerValue = CoConstants.EMPTY_STRING;
							if(edEligibilityCargo.getActivityType().equalsIgnoreCase("RE")){
								moreInfoCode = "CaseClosedInError";
								moreInfoHeaderCode="CaseClosedInErrorHeader";
							}
							else if(edEligibilityCargo.getActivityType().equalsIgnoreCase("IN")){
								moreInfoCode = "LISApplicationValue";
								moreInfoHeaderCode="LISApplicationHeader";
							}
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							headerValue=getTextValue(moreInfoHeaderCode, edEligibilityCargo);
							if (moreInfoText.length() > 0) {
								ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								//r2
								eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
								explanationText=new ExplanationOfBenefits();
								eligibilityHCCTable.setPerson(name);
								eligibilityHCCTable.setBenefitSpan(benefitSpan);
								eligibilityHCCTable.setCoverageCategory(coverageCategory);
								eligibilityHCCTable.setStatus("Approved");
								eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
								String edCode[]=moreInfoCode.split("-");
							    if(edCode.length>0){
								eligibilityHCCTable.setEdCode(edCode[0]);
							    }
							    else{
							    	eligibilityHCCTable.setEdCode(moreInfoCode);
							    }
								if(headerValue.length()>0){
									explanationText.setExplanationHeader(headerValue);
									}else{
									explanationText.setExplanationHeader("SAMPLE HEADER");
									}
								explanationText.setExplanationValue(moreInfoText);
								eligibilityHCCTables.add(eligibilityHCCTable);
								
								
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
								if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
									finalHccMap.put(individualId, approvedList);
								}
									finalHccMap.get(individualId).add(eligibilityHCCTable);
								
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText =  moreInfoText;

								}
							}
							
						}//End of reopened case
						//R2:client share
						//End client share
						//R2 disability
						if(null!=disabilityIndvs&&disabilityIndvs.size()>0&&disabilityIndvs.containsKey(individualId)){
							
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							moreInfoHeaderCode = CoConstants.EMPTY_STRING;
							
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
						
							DcDisabilityCargo dcCargo=disabilityIndvs.get(individualId);
							if(dcCargo.getTypeCd().equalsIgnoreCase("IN")){
								moreInfoHeaderCode="MeetsIncapacityrequirementsHeader";
								moreInfoCode="MeetsIncapacityrequirementsValue";
								tempValue=edEligibilityCargo.getBenefitStatus();
								edEligibilityCargo.setBenefitStatus(noeMedicaid.getStringDate(dcCargo.getReviewDt()));
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								headerValue=getTextValue(moreInfoHeaderCode, edEligibilityCargo);
							}else{
								tempValue=edEligibilityCargo.getBenefitStatus();
								edEligibilityCargo.setBenefitStatus(noeMedicaid.getStringDate(dcCargo.getReviewDt()));
								moreInfoHeaderCode="MeetsdisabilityrequirementsHeader";
								moreInfoCode="MeetsdisabilityrequirementsValue";
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								headerValue=getTextValue(moreInfoHeaderCode, edEligibilityCargo);
						}
							if (moreInfoText.length() > 0) {

								ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								//r2
								eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
								explanationText=new ExplanationOfBenefits();
								eligibilityHCCTable.setPerson(name);
								eligibilityHCCTable.setBenefitSpan(benefitSpan);
								eligibilityHCCTable.setCoverageCategory(coverageCategory);
								eligibilityHCCTable.setStatus("Approved");
								
								eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
								String edCode[]=moreInfoCode.split("-");
							    if(edCode.length>0){
								eligibilityHCCTable.setEdCode(edCode[0]);
							    }
							    else{
							    	eligibilityHCCTable.setEdCode(moreInfoCode);
							    }
								if(headerValue.length()>0){
									explanationText.setExplanationHeader(headerValue);
									}else{
									explanationText.setExplanationHeader("SAMPLE HEADER");
									}
								explanationText.setExplanationValue(moreInfoText);
								eligibilityHCCTables.add(eligibilityHCCTable);
								
								
								if(!infoMap.containsKey(moreInfoCode)){
								infoMap.put(moreInfoCode, explanationText);
								}
								if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(individualId)){
									finalHccMap.put(individualId, approvedList);
								}
									finalHccMap.get(individualId).add(eligibilityHCCTable);
							
								
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText =  moreInfoText;

								}
							
							}
						
						}
						//END r2 disability
					}
					if(previousRowText != null && currentRowText != null && previousRowText.length()>0 && !previousRowText.equalsIgnoreCase(CoConstants.SPACE) && previousRowText.equalsIgnoreCase(currentRowText)){
						//ND-15082: BEGIN
						for(BenefitApprovalInfo approvalInfo: tempApprovalInfos){	
							if(approvalInfo.getMoreInfo() != null && approvalInfo.getMoreInfo().length()>0 && !approvalInfo.getMoreInfo().equalsIgnoreCase(CoConstants.SPACE)){
								approvalInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
								eligibilityHCCTable.setNoeHccExplanation(CoConstants.SAME_AS_ABOVE);
							}							
							benefitApprovalInfos.add(approvalInfo);
							break;
						}
//						for(BenefitApprovalInfo approvalInfo: tempApprovalInfos){
//							String span = approvalInfo.getBenefitSpan();
//							String temp = approvalInfo.getMoreInfo();
//							if((span == null || span.equalsIgnoreCase(CoConstants.EMPTY_STRING) || span.equalsIgnoreCase(CoConstants.SPACE)) && text.length()>0 &&  temp != null && temp.equalsIgnoreCase(text)){
//								//do not add co-payments do not apply info
//							}else{
//								approvalInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
//								benefitApprovalInfos.add(approvalInfo);
//							}	
//						}
//						previousRowText = currentRowText;
						//ND-15082: END
						
					}else{
						benefitApprovalInfos.addAll(tempApprovalInfos);	
						eligibilityHCCTable.setNoeHccExplanation("See point "+hccpoint+" below");
						hccpoint++;
					}
					if(currentRowText != null && currentRowText.trim().length()>0 && !currentRowText.equalsIgnoreCase(CoConstants.SPACE)){
						previousRowText = currentRowText;
					}
					//eligibilityHCCTables.add(eligibilityHCCTable);
				}
				benefitApprovedMemberInfo.setBenefitApprovalInfo(benefitApprovalInfos);
				
				//DO NOT add benefitApprovedMemberInfo if benefitApprovalInfos is empty
				if (benefitApprovedMemberInfo.getBenefitApprovalInfo().size() > 0) {
					approvedMembers.add(benefitApprovedMemberInfo);
				}
				
			}
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while populating Approval Table");
			CoDebugger.debugException(e.getMessage(), e);
		}
		//MSP approved details & disability &  Incapacity requirements
		try{
			if(null!=mspApprovedMap && mspApprovedMap.size()>0){
				List<String>indvMSP=new ArrayList<String>();
				indvMSP.addAll(mspApprovedMap.keySet());
				HashMap<String,String> mspApprovedRes=noeMedicaid.getMSPApprovedForLIS
							  (caseNumber, indvMSP);
				String moreInfoCode = CoConstants.EMPTY_STRING;
				String moreInfoText = CoConstants.EMPTY_STRING;
				String headerValue = CoConstants.EMPTY_STRING;
				String currentRowText=CoConstants.EMPTY_STRING;
				String moreInfoHeaderCode="CaseClosedInErrorHeader";
					if(null != mspApprovedRes && mspApprovedRes.size()>0){
					for(String indv:mspApprovedMap.keySet()){
						List<EdEligibilityCargo>cargos= mspApprovedMap.get(indv);
						for(EdEligibilityCargo edEligibilityCargo:cargos){
							
							String periodStartDate = getDateAsString(edEligibilityCargo.getBenefitStartDt());
							String periodEndDate = getDateAsString(edEligibilityCargo.getBenefitEndDt());
							String benefitSpan=null;
							//r2
							String name = indvIdNameMap.get(indv);
							if(null!=periodEndDate && periodEndDate.length()>0){
								 benefitSpan = periodStartDate + " - " + periodEndDate;
							}else{
								 benefitSpan = "As of "+periodStartDate ;
							}
							String coverageCategory =CoConstants.SPACE;
							String coverageCode=CoConstants.SPACE;
							if (edEligibilityCargo.getCoverageGroup() != null) {
								coverageCode = edEligibilityCargo.getCoverageGroup();
								coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
							}
							moreInfoHeaderCode = "LISApplicationHeader";
							moreInfoCode="LISApplicationValue";
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							headerValue = getTextValue(moreInfoHeaderCode, edEligibilityCargo);
							
							if (moreInfoText.length() > 0) {
							ArrayList<NoticeOfEligibilityHCCTable> approvedList = new ArrayList<NoticeOfEligibilityHCCTable>();
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
						
							eligibilityHCCTable =new NoticeOfEligibilityHCCTable();
							explanationText=new ExplanationOfBenefits();
							eligibilityHCCTable.setPerson(name);
							eligibilityHCCTable.setBenefitSpan(benefitSpan);
							eligibilityHCCTable.setCoverageCategory(coverageCategory);
							eligibilityHCCTable.setStatus("Approved");
							eligibilityHCCTable.setStartDate(edEligibilityCargo.getBenefitStartDt());
							String edCode[]=moreInfoCode.split("-");
						    if(edCode.length>0){
							eligibilityHCCTable.setEdCode(edCode[0]);
						    }
						    else{
						    	eligibilityHCCTable.setEdCode(moreInfoCode);
						    }
							if(headerValue.length()>0){
								explanationText.setExplanationHeader(headerValue);
								}else{
								explanationText.setExplanationHeader("SAMPLE HEADER");
								}
							explanationText.setExplanationValue(moreInfoText);
							eligibilityHCCTables.add(eligibilityHCCTable);
							
							
							if(!infoMap.containsKey(moreInfoCode)){
							infoMap.put(moreInfoCode, explanationText);
							}
							if(finalHccMap.isEmpty() ||!finalHccMap.containsKey(indv)){
								finalHccMap.put(indv, approvedList);
							}
								finalHccMap.get(indv).add(eligibilityHCCTable);
						
							benefitSpan = CoConstants.SPACE;
							coverageCategory = CoConstants.SPACE;
							if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
								currentRowText =  moreInfoText;

							}
						}
					}	
					}		
						
					}	
				}
				
			}catch(Exception e){
			
		}
		
		
		
		// CHANGED MEMEBER TABLE DATA
		try {
			for (String indvId : changedIndvIdEdEligibilityCargosMap.keySet()) {
				
				benefitChangedMemberInfo = new BenefitChangedMemberInfo();
				benefitChangeInfos = new ArrayList<BenefitChangeInfo>();				
				benefitChangedMemberInfo.setFlag(CoConstants.N);
				
				
				String individualId = indvId;
				String name = indvIdNameMap.get(individualId);
				benefitChangedMemberInfo.setName(name);
				
				String coverageCategory;
				String coverageCode;
				String moreInfoCode;
				String moreInfoHeader;
				String moreInfoText;
				String edgTraceId;
				String reasonCode;
				String reasonText;
				String periodStartDate;
				String periodEndDate;
				String toa;
				String benefitSpan;
				String displayFilter;
				ArrayList<String> noticeReasonCodesForEdgTraceId;
				String tempValue=null;

				String previousRowText = "";
				for (EdEligibilityCargo edEligibilityCargo : changedIndvIdEdEligibilityCargosMap.get(indvId)) {
					
					change for ND-17133 conditional display of text in Change table section
					if(benefitChangedMemberInfo.getFlag().equalsIgnoreCase(CoConstants.N) && !authorizedBefore && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")){
						benefitChangedMemberInfo.setFlag(CoConstants.Y);
					}
					
					String currentRowText = "";
					String prefix = "";
					List<BenefitChangeInfo> tempChangeInfos = new ArrayList<BenefitChangeInfo>();
					if (benefitChangedMemberInfo.getApplicationDate() == null) {
						benefitChangedMemberInfo.setApplicationDate(getDateAsString(edEligibilityCargo.getApplicationDt()));
					}
					
					edgTraceId = String.valueOf(edEligibilityCargo.getEdgTraceId());
					noticeReasonCodesForEdgTraceId = edgTraceIdNoticeReasonCodeMap.get(edgTraceId);

					periodStartDate = getDateAsString(edEligibilityCargo.getBenefitStartDt());
					periodEndDate = getDateAsString(edEligibilityCargo.getBenefitEndDt());
					benefitSpan = periodStartDate + " - " + periodEndDate;
					//R2
					//eligibilityHCCTable.setNoeBenefitSpan(benefitSpan);
					
					coverageCategory = CoConstants.SPACE;
					coverageCode = CoConstants.EMPTY_STRING;
					moreInfoCode = CoConstants.EMPTY_STRING;
					moreInfoText = CoConstants.EMPTY_STRING;

					if (edEligibilityCargo.getCoverageGroup() != null) {
						coverageCode = edEligibilityCargo.getCoverageGroup();
						coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
					}
					
					//ND-17025: BEGIN
					if ((coverageCategory == null || coverageCategory.trim().length()==0) 
							&& !edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0") 
							&& (edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_DENIED)
									|| edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_TERMINATED))) {
						coverageCode = CoConstants.COVERAGEGROUPCODE_MA;
						coverageCategory = coverageCodeDescriptionMap.get(coverageCode);
					}
					//ND-17105: END
					
					//ND-15425: #2 BEGIN Do not show row if coverage category is invalid
					if (coverageCategory != null && coverageCategory.length() > 0 && !coverageCategory.equalsIgnoreCase(CoConstants.SPACE)) {
						if (noticeReasonCodesForEdgTraceId != null && noticeReasonCodesForEdgTraceId.size() > 0) {

							for (String noticeReasonCode : noticeReasonCodesForEdgTraceId) {
								if (noticeReasonCodeRefDataMap == null) {
									noticeReasonCodeRefDataMap = getNoticeReasonCodeRefDataMap(edgTraceIdNoticeReasonCodeMap);
								}

								benefitChangeInfo = new BenefitChangeInfo();
								benefitChangeInfo.setBenefitSpan(benefitSpan);
								benefitChangeInfo.setCoverageCategory(coverageCategory);

								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								moreInfoText = CoConstants.SPACE;
								reasonText = CoConstants.SPACE;

								RefTableData refData = noticeReasonCodeRefDataMap.get(noticeReasonCode);
								displayFilter = "";
								if (refData != null) {
									ArrayList<NoticeOfEligibilityHCCTable> changedList = new ArrayList<NoticeOfEligibilityHCCTable>();
									displayFilter = (String) refData.getRefrTableAddiData().get("DISPLAYFILTER");
									if (displayFilter != null && displayFilter.equalsIgnoreCase("Y")) {
										reasonCode = (String) refData.getRefrTableAddiData().get("NOTICE");
										moreInfoCode = (String) refData.getRefrTableAddiData().get("REASONDESC");
										reasonText = getTextValue(reasonCode, edEligibilityCargo);
										moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
										String edCode[]=moreInfoCode.split("-");
									    if(edCode.length>0){
										eligibilityHCCTable.setEdCode(moreInfoCode);
									    }
									    else{
									    	eligibilityHCCTable.setEdCode(moreInfoCode);
									    }

										explanationText.setExplanationValue(moreInfoText);
										
										if(reasonText.length()>0){
										explanationText.setExplanationHeader(reasonText);
										}else{
										explanationText.setExplanationHeader("SAMPLE HEADER");
										}
											if(!infoMap.containsKey(moreInfoCode)){
											infoMap.put(moreInfoCode, explanationText);
											}
											if(finalHccMap.isEmpty()){
												finalHccMap.put(individualId, changedList);
											}else if(!finalHccMap.containsKey(individualId)){
												finalHccMap.put(individualId,changedList);
											}else{
												finalHccMap.get(individualId).add(eligibilityHCCTable);
											}
											eligibilityHCCTables.add(eligibilityHCCTable);
									}
								}
								if (reasonText != null && reasonText.length() > 0 && !reasonText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + reasonText;
									prefix = " ";
								}
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
								}
								benefitChangeInfo.setChangeReason(reasonText);
								benefitChangeInfo.setMoreInfo(moreInfoText);
								//DO NOT add benefitChangeInfo if both reasonText and moreInfoText are blank
								if((reasonText!=null && reasonText.trim().length()>0) || (moreInfoText!=null && moreInfoText.trim().length()>0)){
									tempChangeInfos.add(benefitChangeInfo);
								}								
							}
							if(tempChangeInfos.size()==0 && !edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED)){
								if(previousRowText != null && previousRowText.trim().length()>0){
									currentRowText = previousRowText;
								}
							}
						}
					}
					//ND-15425: #2 END

					toa = edEligibilityCargo.getTypeOfAssistanceCd();
					//ND-15425: #2 BEGIN Do not show row if coverage category is invalid
					if (coverageCategory != null && coverageCategory.length() > 0 && !coverageCategory.equalsIgnoreCase(CoConstants.SPACE)) {
						if (edEligibilityCargo.getCgStatusCd().equalsIgnoreCase(CoConstants.CG_STATUS_CD_CERTIFIED)) {
							if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) {
								displayNoticeAttachment = CoConstants.Y;
							}
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;

							// ND-14313: BEGIN
							boolean emergencyServices = false;
							if (toa != null && toa.equalsIgnoreCase("MA88")) {
								emergencyServices = true;
							}
							boolean medicallyFrailInstitutionalized = false;
							boolean medicallyFrailNotInstitutionalized = false;
							boolean transitionalIncome = false;
							boolean transitionalSpousal = false;
							String coe = edEligibilityCargo.getCoe();
							if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MA) && coe != null){
								if( coe.equalsIgnoreCase("M060") || coe.equalsIgnoreCase("M065") ) {
									medicallyFrailInstitutionalized = true;
								}else if( coe.equalsIgnoreCase("M058") || coe.equalsIgnoreCase("M059") ) {
									medicallyFrailNotInstitutionalized = true;
								}else if( coe.equalsIgnoreCase("M086") || coe.equalsIgnoreCase("M087") ) {
									transitionalIncome = true;
								}else if( coe.equalsIgnoreCase("M061") || coe.equalsIgnoreCase("M088") ) {
									transitionalSpousal = true;
								}									
							}
							//ND-15309: END
							
							if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MA) && !emergencyServices) { // ND-14313:																												// END
								//ND-15309: BEGIN
								if(medicallyFrailInstitutionalized){
									moreInfoCode = "ACA013501PassMedicallyFrailInstitutionalized";
								}else if(medicallyFrailNotInstitutionalized){
									moreInfoCode = "ACA013502PassMedicallyFrailNotInstitutionalized";
								}else{
									moreInfoCode = "ACA.01.27 – Pass Medicaid";
								}
								//ND-15309: END
							} else if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) {
								moreInfoCode = "ACA.01.36 – Pass Medicaid Expansion";
							} else if (coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_HS)) {
								moreInfoCode = "ACA.01.28 – Healthy Steps";
							} else {
								// No other Category possible
							}
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							if (moreInfoText.length() > 0) {
								benefitChangeInfo = new BenefitChangeInfo();
								benefitChangeInfo.setBenefitSpan(benefitSpan);
								benefitChangeInfo.setCoverageCategory(coverageCategory);
								benefitChangeInfo.setChangeReason(CoConstants.SPACE);
								benefitChangeInfo.setMoreInfo(moreInfoText);
								tempChangeInfos.add(benefitChangeInfo);
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
								}
							}
							
							//ND-15560: BEGIN
							if (edEligibilityCargo.getExpandedPraSw() ==CoConstants.CHAR_Y)  {
								moreInfoCode = CoConstants.EMPTY_STRING;
								moreInfoText = CoConstants.EMPTY_STRING;
								moreInfoCode = "ACA0137FailMedicallyFrail";
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								if (moreInfoText.length() > 0) {
									benefitChangeInfo = new BenefitChangeInfo();
									benefitChangeInfo.setBenefitSpan(benefitSpan);
									benefitChangeInfo.setCoverageCategory(coverageCategory);
									benefitChangeInfo.setChangeReason(CoConstants.SPACE);
									benefitChangeInfo.setMoreInfo(moreInfoText);
									tempChangeInfos.add(benefitChangeInfo);
									benefitSpan = CoConstants.SPACE;
									coverageCategory = CoConstants.SPACE;
									if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
										currentRowText = currentRowText + prefix + moreInfoText;
										prefix = " ";
									}
								}
							}
							moreInfoCode = CoConstants.EMPTY_STRING;
							moreInfoText = CoConstants.EMPTY_STRING;
							if(transitionalIncome){
								moreInfoCode = "ACA0132Income";
								moreInfoHeader="ACA0132IncomeHeader";
							}else if(transitionalSpousal){
								moreInfoCode = "ACA0131SpousalSupport";
							}							
							moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
							if (moreInfoText.length() > 0) {
								benefitChangeInfo = new BenefitChangeInfo();
								benefitChangeInfo.setBenefitSpan(benefitSpan);
								benefitChangeInfo.setCoverageCategory(coverageCategory);
								benefitChangeInfo.setChangeReason(CoConstants.SPACE);
								benefitChangeInfo.setMoreInfo(moreInfoText);
								tempChangeInfos.add(benefitChangeInfo);
								benefitSpan = CoConstants.SPACE;
								coverageCategory = CoConstants.SPACE;
								if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
									currentRowText = currentRowText + prefix + moreInfoText;
									prefix = " ";
								}
							}							
							//ND-15560: END

							if (toa != null) {
								moreInfoCode = CoConstants.EMPTY_STRING;
								moreInfoText = CoConstants.EMPTY_STRING;
								//ND-16704					if (toa.equalsIgnoreCase("MA20") && edEligibilityCargo.getCepSw() != CoConstants.YES_CHAR) {
								if (toa.equalsIgnoreCase("MA20") && edEligibilityCargo.getCepSw() != CoConstants.YES_CHAR && edEligibilityCargo.getPriorMedicalCd() != null && edEligibilityCargo.getPriorMedicalCd().equalsIgnoreCase("P0")) {
									moreInfoCode = "ACA.01.30 – Extended";
									tempValue=edEligibilityCargo.getBenefitStatus();
									edEligibilityCargo.setBenefitStatus(asOfDate);
								} else if (!transitionalSpousal && toa.equalsIgnoreCase("MA80")) {
									moreInfoCode = "ACA.01.31 – Spousal Support";
								} else if (!transitionalIncome && toa.equalsIgnoreCase("MA79")) {
									moreInfoCode = "ACA.01.32 – Income";
								} else if (toa.equalsIgnoreCase("MA88")) {
									moreInfoCode = "ACA.01.33 – Emergency Services";
								}
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								if(moreInfoCode.equalsIgnoreCase("ACA.01.30 – Extended")){
									edEligibilityCargo.setBenefitStatus(tempValue);
									tempValue=null;
								}
								if (moreInfoText.length() > 0) {
									benefitChangeInfo = new BenefitChangeInfo();
									benefitChangeInfo.setBenefitSpan(benefitSpan);
									benefitChangeInfo.setCoverageCategory(coverageCategory);
									benefitChangeInfo.setChangeReason(CoConstants.SPACE);
									benefitChangeInfo.setMoreInfo(moreInfoText);
									// benefitChangeInfos
									tempChangeInfos.add(benefitChangeInfo);
									benefitSpan = CoConstants.SPACE;
									coverageCategory = CoConstants.SPACE;
									if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
										currentRowText = currentRowText + prefix+ moreInfoText;
										prefix = " ";
									}
								}
							}
							// ND-15318: BEGIN
							if (edEligibilityCargo.getCopayAmt() == 0 && !coverageCode.equalsIgnoreCase(CoConstants.COVERAGEGROUPCODE_MX)) { // ND-15318:END
								moreInfoCode = CoConstants.EMPTY_STRING;
								moreInfoText = CoConstants.EMPTY_STRING;
								moreInfoCode = "ACA.01.29 – No CoPay";
								moreInfoText = getTextValue(moreInfoCode, edEligibilityCargo);
								if (moreInfoText.length() > 0) {
									benefitChangeInfo = new BenefitChangeInfo();
									benefitChangeInfo.setBenefitSpan(benefitSpan);
									benefitChangeInfo.setCoverageCategory(coverageCategory);
									benefitChangeInfo.setChangeReason(CoConstants.SPACE);
									benefitChangeInfo.setMoreInfo(moreInfoText);
									tempChangeInfos.add(benefitChangeInfo);
									benefitSpan = CoConstants.SPACE;
									coverageCategory = CoConstants.SPACE;
									if (moreInfoText != null && moreInfoText.length() > 0 && !moreInfoText.equalsIgnoreCase(CoConstants.SPACE)) {
										currentRowText = currentRowText + prefix + moreInfoText;
										prefix = " ";
									}
								}
							}
						}
					}
					if(previousRowText != null && currentRowText != null && previousRowText.length()>0 && !previousRowText.equalsIgnoreCase(CoConstants.SPACE) && previousRowText.equalsIgnoreCase(currentRowText)){
							
						for(BenefitChangeInfo changeInfo: tempChangeInfos){
							if(changeInfo.getChangeReason() != null && changeInfo.getChangeReason().length()>0 && !changeInfo.getChangeReason().equalsIgnoreCase(CoConstants.SPACE)){
								changeInfo.setChangeReason(CoConstants.SAME_AS_ABOVE);
							}
							
							if(changeInfo.getMoreInfo() != null && changeInfo.getMoreInfo().length()>0 && !changeInfo.getMoreInfo().equalsIgnoreCase(CoConstants.SPACE)){
								changeInfo.setMoreInfo(CoConstants.SAME_AS_ABOVE);
							}
							
							//ND-15082: BEGIN
							benefitChangeInfos.add(changeInfo);
							break;
//							if((span == null || span.equalsIgnoreCase(CoConstants.EMPTY_STRING) || span.equalsIgnoreCase(CoConstants.SPACE)) && text.length()>0 &&  temp != null && temp.equalsIgnoreCase(text)){
//								//do not add co-payments do not apply info
//							}else{
//								benefitChangeInfos.add(changeInfo);
//							}
							//ND-15082: BEGIN
							
						}
					}else{
						benefitChangeInfos.addAll(tempChangeInfos);	
					}
					if(currentRowText != null && currentRowText.trim().length()>0 && !currentRowText.equalsIgnoreCase(CoConstants.SPACE)){
						previousRowText = currentRowText;
					}
				}
				benefitChangedMemberInfo.setBenefitChangeInfo(benefitChangeInfos);
				//DO NOT add benefitChangedMemberInfo if benefitChangeInfos is empty
				if (benefitChangedMemberInfo.getBenefitChangeInfo().size() > 0) {
					changedMembers.add(benefitChangedMemberInfo);
				}
				//eligibilityHCCTables.add(eligibilityHCCTable);
			}
		} catch (Exception e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while populating Change Table");
			CoDebugger.debugException(e.getMessage(), e);
		}

		try {
			Object[] vCoManualDataValuesCargoArr = null;
			vCoManualDataValuesCargoArr = coDAOServices.getVCoManualDataValues(coCorrespondence.getCoReqSeq());

			VCoManualDataValuesCargo vCoManualDataValuesCargo = null;

			if (null != vCoManualDataValuesCargoArr && vCoManualDataValuesCargoArr.length > 0) {
				vCoManualDataValuesCargo = new VCoManualDataValuesCargo();
				for (int index = 0; index < vCoManualDataValuesCargoArr.length; index++) {
					vCoManualDataValuesCargo = (VCoManualDataValuesCargo) vCoManualDataValuesCargoArr[index];
					if (vCoManualDataValuesCargo.getFieldName().equals(CoConstants.FREE_FORM_TEXT)) {
						additionalComments = vCoManualDataValuesCargo.getFieldContent();
						if (additionalComments == null || ("").equals(additionalComments)) {
							additionalComments = CoConstants.SPACE;
						}
					}
//					JIRA:ND-37338: Removed Legal Cites
//					if (vCoManualDataValuesCargo.getFieldName().equals(CoConstants.LEGAL_CITES)) {
//						legalCites = vCoManualDataValuesCargo.getFieldContent();
//						if (legalCites == null || legalCites == "") {
//							legalCites = CoConstants.SPACE;
//						}
//					}
				}
			}
		} catch (NoDataFoundException e) {
			CoDebugger.debugInformation("Exception: Notice of Eligibility Determination  -->  CaseNumber: " + caseNumber + " --> while populating manual data");
			CoDebugger.debugException("NoDataFoundException", e);
		}

		benefitDeniedMembers.setBenefitDeniedMember(deniedMembers);
		benefitClosedMembers.setBenefitClosedMember(closedMembers);
		benefitApprovedMembers.setBenefitApprovedMember(approvedMembers);
		benefitChangedMembers.setBenefitChangedMember(changedMembers);
		eligibilityDetermination.setDisplayDeniedTable(displayDeniedTable);
		eligibilityDetermination.setDisplayClosedTable(displayClosedTable);
		eligibilityDetermination.setDisplayApprovedTable(displayApprovedTable);
		eligibilityDetermination.setDisplayChangedTable(displayChangedTable);
		eligibilityDetermination.setDisplayNoticeAttachment(displayNoticeAttachment);

		eligibilityDetermination.setBenefitDeniedMembers(benefitDeniedMembers);
		eligibilityDetermination.setBenefitClosedMembers(benefitClosedMembers);
		eligibilityDetermination.setBenefitApprovedMembers(benefitApprovedMembers);
		eligibilityDetermination.setBenefitChangedMembers(benefitChangedMembers);
		eligibilityDetermination.setAdditionalComments(additionalComments);
		eligibilityDetermination.setLegalCites(legalCites);

		if (displayNoticeAttachment.equalsIgnoreCase(CoConstants.Y)) {
			eligibilityDetermination.setNoticeAttachment(populateAttachment(noticeFieldCodeDescriptionMap));
		}
		
		// Logic Added for Telephone Assistance Code Secondary Form for NoE-BEGIN
		if(manuallyGenrated){
			Object[] vCoManualDataValuesCargoArr = null;
			VCoManualDataValuesCargo vCoManualDataValuesCargo = null;
			try {
				vCoManualDataValuesCargoArr = coDAOServices.getVCoManualDataValues(coCorrespondence.getCoReqSeq());
				if (null != vCoManualDataValuesCargoArr && vCoManualDataValuesCargoArr.length > 0) {
					vCoManualDataValuesCargo = new VCoManualDataValuesCargo();
					for (int index = 0; index < vCoManualDataValuesCargoArr.length; index++) {
						vCoManualDataValuesCargo = (VCoManualDataValuesCargo) vCoManualDataValuesCargoArr[index];
						if (vCoManualDataValuesCargo.getFieldName().equals(CoConstants.NOTICE_ATTACHMENTS) &&
								vCoManualDataValuesCargo.getFieldContent().equalsIgnoreCase(CoConstants.TELEPHONE_ATTACHMENT)) {
							noticeData.setTelephoneAssistanceCertificate(setTelephoneAssistanceNoticeData(noticeData, coCorrespondence));		
						} 
					}
				}
			} catch (NoDataFoundException e) {
				CoDebugger.debugException("NoDataFoundException", e);
			}
		}
		else{
		CoRequestHistoryCargo[] coRequestHistoryCargo = (CoRequestHistoryCargo[])coDAOServices.getCoRequestHistoryByCoReqSeq(coCorrespondence.getCoReqSeq());
		boolean hccSnapFlag=false;
		if(coRequestHistoryCargo != null && coRequestHistoryCargo.length>0 && coRequestHistoryCargo[0] != null){
			String attachmentTAC = coRequestHistoryCargo[0].getAttachments();
			if(null!=attachmentTAC){
				hccSnapFlag=attachmentTAC.contains("NDMAELN11");
			}
		}
		
		//hccSnapFlag=true;
		if(hccSnapFlag){
			noticeData.setTelephoneAssistanceCertificate(setTelephoneAssistanceNoticeData(noticeData, coCorrespondence));	
			}
		}
		
		// Logic Added for Telephone Assistance Certificate-END
		//commented for R2
		//noticeData.setEligibilityDetermination(eligibilityDetermination);
		//R2
		Iterator entries = (Iterator) infoMap.entrySet();
		
		while (entries.hasNext()) {
		 String Entry=(String) entries.next();
		 Map.Entry me = (Map.Entry)entries.next();
		 NoeHccExplanationText explanationTextResults=(NoeHccExplanationText) me.getValue();
		 explanationTextsList.add(explanationTextResults);
		 
		}
		
		HashMap<String, ArrayList<NoticeOfEligibilityHCCTable>> sortedRes= noeMedicaid.sortByHCCBenefitStartDate(finalHccMap);
	    HashMap result= (HashMap) noeMedicaid.sortHCcEligibilty(sortedRes, infoMap);  
	   
	    for(String indv:sortedRes.keySet()){
	    	
	    	
	    }
	    
	    finalHccMap= (HashMap<String, ArrayList<NoticeOfEligibilityHCCTable>>) result.get("eligibiltyMap");
	    infoMap=(HashMap<String, ExplanationOfBenefits>) result.get("infoMap");
	    ArrayList<ExplanationOfBenefits>explanationList=(ArrayList<ExplanationOfBenefits>) result.get("explanationList");
	    Iterator iterator = infoMap.entrySet().iterator();
        
	    while (iterator.hasNext()) {
             Map.Entry me2 = (Map.Entry) iterator.next();
             ExplanationOfBenefits explanationTextResults=(ExplanationOfBenefits) me2.getValue();
             explanationTextsList.add(explanationTextResults);
        } 
        
	
	    for(String indvId:finalHccMap.keySet()){
        	List<NoticeOfEligibilityHCCTable> hccTables=finalHccMap.get(indvId);
        	for(int i=0;i<hccTables.size();i++){
        		hccTables.get(i).setEdCode("code");
        		fianleligibilityHCCTables.add(hccTables.get(i));
        	}
        }
        eligibilityHCC.setNoticeOfEligibilityHCCTable(fianleligibilityHCCTables);
		eligibilityHCC.setExplanationOfBenefits(explanationList);
		noticeOfEligibility.setNoticeOfEligibilityHCC(eligibilityHCC);
		// commented to get R1 NOE
		noticeOfEligibility.setDisplayHCCTable(displayHcc);
		noticeData.setNoticeOfEligibility(noticeOfEligibility);
		
		chimesCorrespondence.setMetaData(metaData);
		chimesCorrespondence.setNoticeData(noticeData);

		return chimesCorrespondence;

	}
		
	private TelephoneAssitanceCert setTelephoneAssistanceNoticeData(NoticeData noticeData,COCorrespondence coCorrespondence) throws CoException{

		TelephoneAssitanceCert telephoneAssitanceCert = new TelephoneAssitanceCert();
		try {
			String headOfHold = getHOHInformation(Long.parseLong(coCorrespondence.getCaseAppNumber()));
			telephoneAssitanceCert.setCaseContactName(headOfHold);
		} catch (Exception e) {
			CoDebugger.debugException("getHOHInformation:" + e.getMessage(), e);
		}

		// telephoneAssitanceCert.setWorkerNotes(getWorkerNotes(coCorrespondence));

		String attPhoneNum = ReferenceTableAccess.getRefDescription(CoConstants.ATTNUM, CoConstants.REF_TABLE_NOTICEFIELDS);
		String verizonPhoneNum = ReferenceTableAccess.getRefDescription(CoConstants.VERIZONNUM, CoConstants.REF_TABLE_NOTICEFIELDS);
		String ndContactLink = ReferenceTableAccess.getRefDescription(CoConstants.NDCONTACTLINK, CoConstants.REF_TABLE_NOTICEFIELDS);
		String ndPSCPhoneNum = ReferenceTableAccess.getRefDescription(CoConstants.NDPSCNUM, CoConstants.REF_TABLE_NOTICEFIELDS);
		String ndPSCLink = ReferenceTableAccess.getRefDescription(CoConstants.NDPSCSITE, CoConstants.REF_TABLE_NOTICEFIELDS);
		//ND-42536-Telephone Assistance Certificate
		String sspLink = ReferenceTableAccess.getRefDescription(CoConstants.SSPSITE, CoConstants.REF_TABLE_NOTICEFIELDS);
		

		telephoneAssitanceCert.setAttPhoneNumber(attPhoneNum);
		telephoneAssitanceCert.setVerizonPhoneNumber(verizonPhoneNum);
		telephoneAssitanceCert.setNdContactLink(ndContactLink);
		telephoneAssitanceCert.setNdPSCPhoneNumber(ndPSCPhoneNum);
		telephoneAssitanceCert.setNdPSCLink(ndPSCLink);
		//ND-42536-Telephone Assistance Certificate
		telephoneAssitanceCert.setSspLink(sspLink);
		//HPE-Control Flag
		telephoneAssitanceCert.setTelephoneAssistanceFlagHPE(CoConstants.YES_STRING_Y);

		Object[] vCoManualDataValuesCargoArr = null;
		VCoManualDataValuesCargo vCoManualDataValuesCargo = null;
		TextTable textTable = new TextTable();
		try {
			vCoManualDataValuesCargoArr = coDAOServices.getVCoManualDataValues(coCorrespondence.getCoReqSeq());
			if (null != vCoManualDataValuesCargoArr && vCoManualDataValuesCargoArr.length > 0) {
				vCoManualDataValuesCargo = new VCoManualDataValuesCargo();
				for (int index = 0; index < vCoManualDataValuesCargoArr.length; index++) {
					vCoManualDataValuesCargo = (VCoManualDataValuesCargo) vCoManualDataValuesCargoArr[index];
					if (vCoManualDataValuesCargo.getFieldName().equals(CoConstants.FREE_FORM_TEXT)) {
						textTable.getText().add(vCoManualDataValuesCargo.getFieldContent());
						noticeData.setFreeformTable(textTable);
					} else {

					}
				}
			}
		} catch (NoDataFoundException e) {
			CoDebugger.debugException("NoDataFoundException", e);
		}
		
		//noticeData.setTelephoneAssistanceCertificate(telephoneAssitanceCert);
		return telephoneAssitanceCert;
	}
	
	

	*//**
	 * 
	 * @param caseNumber
	 * @return
	 *//*
	private HashSet<String> getAllActiveInCaseIndividuals(String caseNumber, String date) {
		DcCaseIndividualCargo[] dcCaseIndividualCargos = null;
		HashSet<String> individualIds = new HashSet<String>();

		try {
			if (caseNumber != null && caseNumber.length() > 0) {
				dcCaseIndividualCargos = (DcCaseIndividualCargo[]) coDAOServices.getDcCaseIndividualByActiveIncaseNonDeceased(Long.valueOf(caseNumber).longValue(), date);
				if (dcCaseIndividualCargos != null && dcCaseIndividualCargos.length > 0) {
					for (DcCaseIndividualCargo dcCaseIndividualCargo : dcCaseIndividualCargos) {
						individualIds.add(String.valueOf(dcCaseIndividualCargo.getIndvId()));
					}
				}
			}
		} catch (Exception ex) {
			CoDebugger.debugException("Error in fetching data from DcCaseIndividual table for caseNumber = " + caseNumber + ex.getMessage(), ex);
		}

		return individualIds;
	}

	*//**
	 * 
	 * @param caseNumber
	 * @param individualIds
	 * @return
	 *//*
	private EdEligibilityCargo[] getEdEligibilityCargosForManualNotice(String caseNumber, HashSet<String> individualIds, String generateDateString) {
		EdEligibilityCargo[] edEligibilityCargos = null;

		try {
			edEligibilityCargos = (EdEligibilityCargo[]) coDAOServices.getEdEligibilityByCurrentEligIndAndActiveInCase(Long.valueOf(caseNumber).longValue(), generateDateString);
		} catch (Exception ex) {
			CoDebugger.debugException("Error in fetching data from ED_ELIGIBILITY table for getEdEligibilityByCase method. caseNumber = " + caseNumber + ex.getMessage(), ex);
		}
		return edEligibilityCargos;
	}

	*//**
	 * 
	 * @param coDaoServices
	 * @param caseNumber
	 * @param docId
	 * @param coReqSeq
	 * @return
	 *//*
	private EdEligibilityCargo[] getEdEligibilityCargosForAutoNotice(CoDAOServices coDaoServices, String caseNumber, String docId, long coReqSeq, String generateDateString) {
		EdEligibilityCargo[] edEligibilityCargos = null;
		CoNodRequestDetailCargo[] coNodRequestDetailCargos = null;
		HashSet<Long> edgNumSet = new HashSet<Long>();
		HashSet<Long> edgTraceIdSet = new HashSet<Long>();
		String edgNumString = "";
		String edgTraceIdString = "";
		try {
			coNodRequestDetailCargos = coDaoServices.getNodDetailByCaseNumDocIdAndReqSeq(caseNumber, docId, coReqSeq);
			if (coNodRequestDetailCargos != null && coNodRequestDetailCargos.length > 0) {
				String prefix1 = "";
				String prefix2 = "";
				for (CoNodRequestDetailCargo coNodRequestDetailCargo : coNodRequestDetailCargos) {
					if (!edgNumSet.contains(coNodRequestDetailCargo.getEdgNum())) {
						edgNumSet.add(coNodRequestDetailCargo.getEdgNum());
						edgNumString = edgNumString + prefix1 + String.valueOf(coNodRequestDetailCargo.getEdgNum());
						prefix1 = ", ";
					}

					if (!edgTraceIdSet.contains(coNodRequestDetailCargo.getEdgTraceId())) {
						edgTraceIdSet.add(coNodRequestDetailCargo.getEdgTraceId());
						edgTraceIdString = edgTraceIdString + prefix2 + String.valueOf(coNodRequestDetailCargo.getEdgTraceId());
						prefix2 = ", ";
					}
				}
			}
		} catch (Exception ex) {
			CoDebugger.debugException("Error in fetching data from CO_NOD_REQUEST_DETAIL table for edg_trace_ids for co_req_seq = " + coReqSeq + ", Doc id = " + docId + ", Case Number = " + caseNumber + ex.getMessage(), ex);
		}

		if (edgNumString != null && edgNumString.length() > 0 && edgTraceIdString != null && edgTraceIdString.length() > 0) {
			try {
				edEligibilityCargos = (EdEligibilityCargo[]) coDAOServices.getEdEligibilityByEdgNumEdgTraceIdAndActiveInCase(Long.valueOf(caseNumber).longValue(), edgNumString, edgTraceIdString, generateDateString);
			} catch (Exception ex) {
				CoDebugger.debugException("Error in fetching data from ED_ELIGIBILITY table forgetEdEligibilityByCase method. caseNumber = " + caseNumber + ex.getMessage(), ex);
			}
		}

		return edEligibilityCargos;
	}

	*//**
	 * 
	 * @param edEligibilityCargos
	 * @return
	 *//*
	private HashMap<String, List<EdEligibilityCargo>> getIndvIdEdEligibilityCargosMap(EdEligibilityCargo[] edEligibilityCargos) {
		HashMap<String, List<EdEligibilityCargo>> indvIdEdEligibilityCargosMap = new HashMap<String, List<EdEligibilityCargo>>();
		if (edEligibilityCargos != null) {
			for (EdEligibilityCargo cargo : edEligibilityCargos) {

				if (cargo != null && cargo.getTargetPersonId() != null) {
					long indvId = cargo.getTargetPersonId().longValue();
					if (!indvIdEdEligibilityCargosMap.containsKey(String.valueOf(indvId))) {
						indvIdEdEligibilityCargosMap.put(String.valueOf(indvId), new ArrayList<EdEligibilityCargo>());
					}
					indvIdEdEligibilityCargosMap.get(String.valueOf(indvId)).add(cargo);
				}
			}
		}

		return indvIdEdEligibilityCargosMap;
	}

	*//**
	 * 
	 * @param coDaoServices
	 * @param caseNumber
	 * @param edgTraceIdsStringForQuery
	 * @return
	 *//*
	private EdEligNoticeReasonsCargo[] getEdEligNoticeReasonCargos(CoDAOServices coDaoServices, String caseNumber, String edgTraceIdsStringForQuery) {
		EdEligNoticeReasonsCargo[] edEligNoticeReasonsCargos = null;
		if (edgTraceIdsStringForQuery != null && edgTraceIdsStringForQuery.length() > 0) {
			try {
				edEligNoticeReasonsCargos = (EdEligNoticeReasonsCargo[]) coDaoServices.getGroupReasons(Long.valueOf(caseNumber), edgTraceIdsStringForQuery.substring(1, edgTraceIdsStringForQuery.length() - 1));
			} catch (Exception ex) {
				CoDebugger.debugException("Error in fetching data from ED_NOTICE_REASONS table for getGroupReasons method. Edg trace Id list = " + edgTraceIdsStringForQuery + ", Case Number = " + caseNumber + CoConstants.SPACE + ex.getMessage(), ex);
			}
		}
		return edEligNoticeReasonsCargos;
	}

	*//**
	 * 
	 * @param coDaoServices
	 * @param caseNumber
	 * @param edgTraceIdsStringForQuery
	 * @return
	 *//*
	private HashMap<String, ArrayList<String>> getEdgTraceIdNoticeReasonCodeMap(CoDAOServices coDaoServices, String caseNumber, String edgTraceIdsStringForQuery) {

		HashMap<String, ArrayList<String>> edgTraceIdNoticeReasonsMap = new HashMap<String, ArrayList<String>>();
		EdEligNoticeReasonsCargo[] edEligNoticeReasonsCargos = null;
		if (edgTraceIdsStringForQuery != null && edgTraceIdsStringForQuery.length() > 0) {
			edEligNoticeReasonsCargos = (EdEligNoticeReasonsCargo[]) getEdEligNoticeReasonCargos(coDaoServices, caseNumber, edgTraceIdsStringForQuery);
		}
		if (edEligNoticeReasonsCargos != null) {
			for (EdEligNoticeReasonsCargo edEligNoticeReasonsCargo : edEligNoticeReasonsCargos) {
				String edgTraceId = String.valueOf(edEligNoticeReasonsCargo.getEdgTraceId());
				if (edgTraceIdNoticeReasonsMap.get(edgTraceId) == null) {
					ArrayList<String> failureReasonCodeList = new ArrayList<String>();
					edgTraceIdNoticeReasonsMap.put(edgTraceId, failureReasonCodeList);
				}
				String failureReasonCode = edEligNoticeReasonsCargo.getFailureReasonCode();
				if (failureReasonCode != null) {
					String[] result = failureReasonCode.split("\\|");
					// temp solution if failureReasonCodes are not pipe
					// separated
					if (result.length == 1 && result[0].length() > 6) {
						result = failureReasonCode.split("(?<=\\G.{6})");
					}
					for (String s : result) {
						edgTraceIdNoticeReasonsMap.get(edgTraceId).add(s);
					}
				}
			}
		}
		return edgTraceIdNoticeReasonsMap;
	}

	*//**
	 * 
	 * @param coDaoServices
	 * @param indvIds
	 * @return
	 *//*
	private HashMap<String, String> getIndvIdNameMap(CoDAOServices coDaoServices, ArrayList<Long> indvIds) {
		HashMap<String, String> indvIdNameMap = new HashMap<String, String>();
		DcIndvCargo[] dcIndvCargos = null;
		long[] indvIdArray = null;

		if (indvIds != null && indvIds.size() > 0) {
			indvIdArray = new long[indvIds.size()];
			for (int i = 0; i < indvIds.size(); i++) {
				indvIdArray[i] = indvIds.get(i);
			}

			try {
				dcIndvCargos = (DcIndvCargo[]) coDAOServices.getIndividualDetails(indvIdArray);

			} catch (Exception ex) {
				CoDebugger.debugException("Error in fetching data from DC_INDV table for findByIndvIds method. indvId List = " + indvIds + "  ," + ex.getMessage(), ex);
			}
		}

		if (dcIndvCargos != null) {
			for (DcIndvCargo dcIndvCargo : dcIndvCargos) {
				String indvId = String.valueOf(dcIndvCargo.getIndvId());
				String firstName = dcIndvCargo.getFirstName();
				String middleName = dcIndvCargo.getMidName();
				String lastName = dcIndvCargo.getLastName();
				String suffix = dcIndvCargo.getSufxName();
				String suffixDesc = "";
				String name = "";
				if (firstName != null && firstName.length() > 0) {
					name += firstName;
				}
				if (middleName != null && middleName.length() > 0) {
					name = name + " " + middleName;
				}
				if (lastName != null && lastName.length() > 0) {
					name = name + " " + lastName;
				}
				if (suffix != null && suffix.length() > 0) {
					try{
						suffixDesc = ReferenceTableAccess.getRefDescription(suffix, CoConstants.REF_TABLE_NAMESUFFIX);
					}catch(Exception e){
						CoDebugger.debugException(e.getMessage(), e);
					}
					if(suffixDesc != null)
						name = name + " " + suffixDesc;
				}
				if (name.length() == 0) {
					name = CoConstants.STRING_UNKNOWN;
				}
				indvIdNameMap.put(indvId, name);
			}
		}

		return indvIdNameMap;
	}

	*//**
	 * 
	 * @param edgTraceIdNoticeReasonCodeMap
	 * @return
	 *//*
	private HashMap<String, RefTableData> getNoticeReasonCodeRefDataMap(HashMap<String, ArrayList<String>> edgTraceIdNoticeReasonCodeMap) {

		RefTableData refTable[] = new RefTableData[5];
		RefTableData refData = new RefTableData();
		HashMap<String, RefTableData> noticeReasonCodeRefDataMap = new HashMap<String, RefTableData>();
		if (edgTraceIdNoticeReasonCodeMap != null && edgTraceIdNoticeReasonCodeMap.size() > 0) {

			try {
				refTable = ReferenceTableAccess.getRefTableData(CoConstants.REF_TABLE_EDREASONCD);
				if (refTable != null && refTable.length > 0) {
					for (int index = 0; index < refTable.length; index++) {
						refData = refTable[index];
						String code = refData.getRefrTableCode();
						noticeReasonCodeRefDataMap.put(code, refData);
					}
				}
			} catch (Exception e) {
				CoDebugger.debugException(e.getMessage(), e);
			}

		}
		return noticeReasonCodeRefDataMap;
	}

	*//**
	 * 
	 * @return
	 *//*
	private HashMap<String, String> getCoverageCodeDescriptionMap() {
		RefTableData refTable[] = new RefTableData[5];
		RefTableData refData = new RefTableData();
		HashMap<String, String> coverageCodeDescriptionMap = new HashMap<String, String>();
		try {
			refTable = ReferenceTableAccess.getRefTableData(CoConstants.REF_TABLE_EDCOVERAGEGROUP);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e);
		}
		if (refTable != null && refTable.length > 0) {
			for (int index = 0; index < refTable.length; index++) {
				refData = refTable[index];
				String code = refData.getRefrTableCode();
				String description = refData.getRefrTableDesc();
				coverageCodeDescriptionMap.put(code, description);
			}
		}
		return coverageCodeDescriptionMap;

	}

	*//**
	 * 
	 * @return
	 *//*
	private HashMap<String, String> getNoticeFieldCodeDescriptionMap() {
		RefTableData refTable[] = new RefTableData[5];
		RefTableData refData = new RefTableData();
		HashMap<String, String> noticeFieldCodeDescriptionMap = new HashMap<String, String>();

		try {
			refTable = ReferenceTableAccess.getRefTableData(CoConstants.REF_TABLE_NOTICEFIELDS);
		} catch (Exception e) {
			CoDebugger.debugException(e.getMessage(), e);
		}

		if (refTable != null && refTable.length > 0) {
			for (int index = 0; index < refTable.length; index++) {
				refData = refTable[index];
				String code = refData.getRefrTableCode();
				String description = refData.getRefrTableDesc();
				noticeFieldCodeDescriptionMap.put(code, description);
			}
		}
		return noticeFieldCodeDescriptionMap;
	}

	*//**
	 * 
	 * @param date
	 * @return
	 *//*
	private String getDateAsString(Timestamp date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String stringDate = CoConstants.EMPTY_STRING;
		if (date != null) {
			try {
				stringDate = dateFormat.format(date);
			} catch (Exception e) {
				CoDebugger.debugException(e.getMessage(), e);
			}

		}
		return stringDate;
	}

	*//**
	 * 
	 * @param code
	 * @return
	 *//*
	private String getTextValue(String code, EdEligibilityCargo edEligibilityCargo) {
		String value = CoConstants.EMPTY_STRING;
		String maxCopay = CoConstants.EMPTY_STRING;
		String shareAmount = CoConstants.EMPTY_STRING;
		

		if (code == null || (code != null && code.length() == 0)) {
			return value;
		} else {
			code = code.replaceAll("[^a-zA-Z0-9]+", "");
		}
		if (code.equalsIgnoreCase("ELIGACA01RecipientLiability")) {
			value = CoEdNoticeConstants.ELIGACA01RecipientLiability;
		} else if(code.equalsIgnoreCase("ACA0201OutOfHousehold")){
			value = CoEdNoticeConstants.ACA0201OutOfHousehold;
		} else if (code.equalsIgnoreCase("ELIGACA02ReasonableOpportunity")) {
			value = CoEdNoticeConstants.ELIGACA02ReasonableOpportunity;
		} else if (code.equalsIgnoreCase("ACA0101OutOfHousehold")) {
			value = CoEdNoticeConstants.ACA0101OutOfHousehold;
		} else if (code.equalsIgnoreCase("ACA0102ExistingInsurance")) {
			value = CoEdNoticeConstants.ACA0102ExistingInsurance;
		} else if (code.equalsIgnoreCase("ACA0202ExistingInsurance")) {
			value = CoEdNoticeConstants.ACA0202ExistingInsurance;
		} else if (code.equalsIgnoreCase("ACA0103LossofContact")) {
			value = CoEdNoticeConstants.ACA0103LossofContact;
		} else if (code.equalsIgnoreCase("ACA0203LossofContact")) {
			value = CoEdNoticeConstants.ACA0203LossofContact;
		} else if (code.equalsIgnoreCase("ACA0104NoQualifyingChildren")) {
			value = CoEdNoticeConstants.ACA0104NoQualifyingChildren;
		} else if (code.equalsIgnoreCase("ACA0204NoQualifyingChildren")) {
			value = CoEdNoticeConstants.ACA0204NoQualifyingChildren;
		} else if (code.equalsIgnoreCase("ACA0105OtherStateBenefits")) {
			value = CoEdNoticeConstants.ACA0105OtherStateBenefitsHeader;
		} else if (code.equalsIgnoreCase("ACA0205OtherStateBenefits")) {
			value = CoEdNoticeConstants.ACA0205OtherStateBenefitsValue;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value.replace("$X", date);
		} else if (code.equalsIgnoreCase("ACA0106DualEligibility")) {
			value = CoEdNoticeConstants.ACA0106DualEligibilityHeader;
		} else if (code.equalsIgnoreCase("ACA0206DualEligibility")) {
			value = CoEdNoticeConstants.ACA0206DualEligibilityValue;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value.replace("$X", date);
		} else if (code.equalsIgnoreCase("ACA0107Residency")) {
			value = CoEdNoticeConstants.ACA0107Residency;
		} else if (code.equalsIgnoreCase("ACA0207Residency")) {
			value = CoEdNoticeConstants.ACA0207Residency;
		} else if (code.equalsIgnoreCase("ACA0108Citizenship")) {
			value = CoEdNoticeConstants.ACA0108Citizenship;
		} else if (code.equalsIgnoreCase("ACA0208Citizenship")) {
			value = CoEdNoticeConstants.ACA0208Citizenship;
		} else if (code.equalsIgnoreCase("ACA0109ROP")) {
			value = CoEdNoticeConstants.ACA0109ROP;
		} else if (code.equalsIgnoreCase("ACA0209ROP")) {
			value = CoEdNoticeConstants.ACA0209ROP;
		} else if (code.equalsIgnoreCase("ACA0110SSN")) {
			value = CoEdNoticeConstants.ACA0110SSN;
		} else if (code.equalsIgnoreCase("ACA0210SSN")) {
			value = CoEdNoticeConstants.ACA0210SSN;
		} else if (code.equalsIgnoreCase("ACA0111PublicInstitution")) {
			value = CoEdNoticeConstants.ACA0111PublicInstitution;
		} else if (code.equalsIgnoreCase("ACA0211PublicInstitution")) {
			value = CoEdNoticeConstants.ACA0211PublicInstitution;
		} else if (code.equalsIgnoreCase("ACA0112PublicInstitution")) {
			value = CoEdNoticeConstants.ACA0112PublicInstitution;
		} else if (code.equalsIgnoreCase("ACA0212PublicInstitution")) {
			value = CoEdNoticeConstants.ACA0212PublicInstitution;
		} else if (code.equalsIgnoreCase("ACA0113PRTF")) {
			value = CoEdNoticeConstants.ACA0113PRTF;
		} else if (code.equalsIgnoreCase("ACA0213PRTF")) {
			value = CoEdNoticeConstants.ACA0213PRTF;
		} else if (code.equalsIgnoreCase("ACA0114IncompleteReview")) {
			value = CoEdNoticeConstants.ACA0114IncompleteReview;
		} else if (code.equalsIgnoreCase("ACA0214IncompleteReview")) {
			value = CoEdNoticeConstants.ACA0214IncompleteReview;
		} else if (code.equalsIgnoreCase("ACA0115NoncooperationChildSupport")) {
			value = StringEscapeUtils.escapeXml(CoEdNoticeConstants.ACA0115NoncooperationChildSupport).replace("&quot;", "\"");
		} else if (code.equalsIgnoreCase("ACA0215NoncooperationChildSupport")) {
			value = CoEdNoticeConstants.ACA0215NoncooperationChildSupport;
		} else if (code.equalsIgnoreCase("ACA0116NoncooperationTPL")) {
			value = StringEscapeUtils.escapeXml(CoEdNoticeConstants.ACA0116NoncooperationTPL).replace("&quot;", "\"");
		} else if (code.equalsIgnoreCase("ACA0216NoncooperationTPL")) {
			value = CoEdNoticeConstants.ACA0216NoncooperationTPL;
		} else if (code.equalsIgnoreCase("ACA0117FailEarnedRights")) {
			value = CoEdNoticeConstants.ACA0117FailEarnedRights;
		} else if (code.equalsIgnoreCase("ACA0217FailEarnedRights")) {
			value = CoEdNoticeConstants.ACA0217FailEarnedRights;
		} else if (code.equalsIgnoreCase("ACA0118PERMCompliance")) {
			value = CoEdNoticeConstants.ACA0118PERMCompliance;
		} else if (code.equalsIgnoreCase("ACA0218PERMCompliance")) {
			value = CoEdNoticeConstants.ACA0218PERMCompliance;
		} else if (code.equalsIgnoreCase("ACA0119HealthyStepsCompliance")) {
			value = CoEdNoticeConstants.ACA0119HealthyStepsCompliance;
		} else if (code.equalsIgnoreCase("ACA0219HealthyStepsCompliance")) {
			value = CoEdNoticeConstants.ACA0219HealthyStepsCompliance;
		} else if (code.equalsIgnoreCase("ACA0120QCCompliance")) {
			value = CoEdNoticeConstants.ACA0120QCCompliance;
		} else if (code.equalsIgnoreCase("ACA0220QCCompliance")) {
			value = CoEdNoticeConstants.ACA0220QCCompliance;			
		} else if (code.equalsIgnoreCase("ACA012101Withdrawn")) {
            value = CoEdNoticeConstants.ACA012101Withdrawn;
        } else if (code.equalsIgnoreCase("ACA012102ClientRequestClosure")) {
            value = CoEdNoticeConstants.ACA012102ClientRequestClosure;
        } else if (code.equalsIgnoreCase("ACA0221Withdrawn")) {
			value = CoEdNoticeConstants.ACA0221Withdrawn;			
		}else if (code.equalsIgnoreCase("ACA0221ClientRequestClosure")) {
            value = CoEdNoticeConstants.ACA0221ClientRequestClosure;           
        } else if (code.equalsIgnoreCase("ACA0122NotCovered")) {
			value = CoEdNoticeConstants.ACA0122NotCovered;
		} else if (code.equalsIgnoreCase("ACA0222NotCovered")) {
			value = CoEdNoticeConstants.ACA0222NotCovered;			
		} else if (code.equalsIgnoreCase("ACA0123MedicaidIneligible")) {
			value = CoEdNoticeConstants.ACA0123MedicaidIneligible;
		} else if (code.equalsIgnoreCase("ACA0223MedicaidIneligible")) {
			value = CoEdNoticeConstants.ACA0223MedicaidIneligible;
		} else if (code.equalsIgnoreCase("ACA0124ExcessIncome")) {
			value = CoEdNoticeConstants.ACA0124ExcessIncome;
		} else if (code.equalsIgnoreCase("ACA0224ExcessIncome")) {
			//CR 1.3 change ND-19743			
			char micap_fap_sw=edEligibilityCargo.getMicapFapSw();
			//micap_fap_sw = Y  if person is one of the following: (1) Parent Caretaker, (2) Pregnant Woman, (3) less than 21 years old.
			if(micap_fap_sw == 'Y'){		
				value = CoEdNoticeConstants.ACA022402ExcessIncome;
				if (value != null && value.length() > 0 && value.contains("$X")) {
					shareAmount = "$0.00";
					double dShareAmount = edEligibilityCargo.getPotGrossBenefitAmt();
					if (dShareAmount > 0) {
						DecimalFormat df = new DecimalFormat("#");
						df.setMaximumFractionDigits(2);
						shareAmount = "$" + df.format(dShareAmount);
					}
					value = value.replace("$X", shareAmount);
				}
			}else{
				value = CoEdNoticeConstants.ACA022401ExcessIncome;
			}
		} else if (code.equalsIgnoreCase("ACA0125MissingVerification")) {
			value = CoEdNoticeConstants.ACA0125MissingVerificationHeader;
		} else if (code.equalsIgnoreCase("ACA0225MissingVerification")) {
			value = CoEdNoticeConstants.ACA0225MissingVerificationR2;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value+CoEdNoticeConstants.RcvDateR2+" "+date+".";
		} else if (code.equalsIgnoreCase("ACA0126Medicare")) {
			value = CoEdNoticeConstants.ACA0126Medicare;
		} else if (code.equalsIgnoreCase("ACA0226Medicare")) {
			value = CoEdNoticeConstants.ACA0226Medicare;
		} else if (code.equalsIgnoreCase("ACA0127PassMedicaid")) {
			value = CoEdNoticeConstants.ACA0127PassMedicaid;
		} else if (code.equalsIgnoreCase("ACA0128HealthySteps")) {
			value = CoEdNoticeConstants.ACA0128HealthySteps;
			if (value != null && value.length() > 0 && value.contains("$X")) {
				maxCopay = "$0.00";
				double copayAmount = edEligibilityCargo.getCopayAmt();
				if (copayAmount > 0) {
					DecimalFormat df = new DecimalFormat("#");
					df.setMaximumFractionDigits(2);
					maxCopay = "$" + df.format(copayAmount);
				}
				value = value.replace("$X", maxCopay);
				String childrenNames=edEligibilityCargo.getBenefitStatus();
				if(childrenNames.length()>0){
					value=value+CoEdNoticeConstants.HealthyStepsChildrenList;
					value = value.replace("$X", childrenNames);
				}
			}
		} else if (code.equalsIgnoreCase("ACA0129NoCoPay")) {
			value = CoEdNoticeConstants.ACA0129NoCoPay;
		} else if (code.equalsIgnoreCase("ACA0130Extended")) {
            
			value = CoEdNoticeConstants.ACA0130ExtendedR2;
			// mapped asofDate to BenefitStatus
			String asOfDate=edEligibilityCargo.getBenefitStatus();
			String bgnDate;
			String endDate;
			Date asdate=CONoticeUtility.parseStringDate(asOfDate);
			Calendar calendar=CONoticeUtility.getCalendar(asdate); 	
			int calDate=calendar.get(Calendar.DATE);
			if(calDate!=1){
				calendar.add(calendar.MONTH, 1);
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
			}
			Date bDate=calendar.getTime();
			bgnDate=CONoticeUtility.parseDateToString(bDate);
			
			calendar.add(Calendar.DATE, 60);
			Date eDate=calendar.getTime();
			endDate=CONoticeUtility.parseDateToString(eDate);
			
			value=value.replace("$X", bgnDate);
			value=value.replace("$Y", endDate);
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value+CoEdNoticeConstants.RcvDateR2+" "+date+".";
		} else if (code.equalsIgnoreCase("ACA0131SpousalSupport")) {
			value = CoEdNoticeConstants.ACA0131SpousalSupportR2;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=CoEdNoticeConstants.RcvDateR2+" "+date+".";
		} else if (code.equalsIgnoreCase("ACA0132Income")) {
			value = CoEdNoticeConstants.ACA0132IncomeR2;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=CoEdNoticeConstants.RcvDateR2+" "+date+".";
		} else if (code.equalsIgnoreCase("ACA0133EmergencyServices")) {
			value = CoEdNoticeConstants.ACA0133EmergencyServicesR2;
		} else if (code.equalsIgnoreCase("ACA0134AlreadyCovered")) {
			value = CoEdNoticeConstants.ACA0134AlreadyCovered;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value.replace("$X", date);
		} else if (code.equalsIgnoreCase("ACA013501PassMedicallyFrailInstitutionalized")) {
			value = CoEdNoticeConstants.ACA013501PassMedicallyFrailInstitutionalized;
		} else if (code.equalsIgnoreCase("ACA013502PassMedicallyFrailNotInstitutionalized")) {
			value = CoEdNoticeConstants.ACA013502PassMedicallyFrailNotInstitutionalized;
		} else if (code.equalsIgnoreCase("ACA0136PassMedicaidExpansion")) {
			value = CoEdNoticeConstants.ACA0136PassMedicaidExpansion;
		} else if (code.equalsIgnoreCase("ACA0137FailMedicallyFrail")) {
			value = CoEdNoticeConstants.ACA0137FailMedicallyFrail;
		} else if (code.equalsIgnoreCase("ACA0138Age")) {
			value = CoEdNoticeConstants.ACA0138Age;
		} else if (code.equalsIgnoreCase("ACA0238Age")) {
			value = CoEdNoticeConstants.ACA0238Age;
		}else if (code.equalsIgnoreCase("ACA0139NotMeetingRequirements")) {
			value = CoEdNoticeConstants.ACA0139NotMeetingRequirements;
		} else if (code.equalsIgnoreCase("ACA0239NotMeetingRequirements")) {
			value = CoEdNoticeConstants.ACA0239NotMeetingRequirements;
		} else if (code.equalsIgnoreCase("ACA0140ChildIsNotReceivingMEC")) {
            value = CoEdNoticeConstants.ACA0140ChildIsNotReceivingMEC;
        } else if (code.equalsIgnoreCase("ACA0240ChildIsNotReceivingMEC")) {
            value = CoEdNoticeConstants.ACA0240ChildIsNotReceivingMEC;
        }else if(code.equalsIgnoreCase("ACA0132IncomeHeader")){//R2
        	value=CoEdNoticeConstants.ACA0132IncomeHeader;
        }else if(code.equalsIgnoreCase("ACA0130ExtendedHeader")){
        	value=CoEdNoticeConstants.ACA0130ExtendedHeader;
        }else if(code.equalsIgnoreCase("CaseClosedInError")){
        	value=CoEdNoticeConstants.CaseClosedInError;
        }else if(code.equalsIgnoreCase("CaseClosedInErrorHeader")){
        	value=CoEdNoticeConstants.CaseClosedInErrorHeader;
        }else if(code.equalsIgnoreCase("ACARefugee")){
        	value=CoEdNoticeConstants.ACARefugee;
        	String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
        	value=CoEdNoticeConstants.RcvDateR2+" "+date+"."; 
        }else if(code.equalsIgnoreCase("ACARefugeeHeader")){
        	value=CoEdNoticeConstants.RefugeeHeader;
        }else if(code.equalsIgnoreCase("ACA0131SpousalSupportHeader")){
        	value=CoEdNoticeConstants.ACA0131SpousalSupportHeader;
        }else if(code.equalsIgnoreCase("ChildrenDisabilitiesCoverage")){
        	value=CoEdNoticeConstants.ChildrenDisabilitiesCoverage;
        }else if(code.equalsIgnoreCase("ChildrenDisabilitiesCoverageHeader")){
        	value=CoEdNoticeConstants.ChildrenDisabilitiesCoverageHeader;
        }else if(code.equalsIgnoreCase("ACAEligibleHealthySteps")){
        	value=CoEdNoticeConstants.ChildrenDisabilitiesCoverageHeader;
        }else if(code.equalsIgnoreCase("ACAEligibleHealthyStepsHeader")){
        	value=CoEdNoticeConstants.ChildrenDisabilitiesCoverageHeader;
        }else if(code.equalsIgnoreCase("WorkersDisabiltyCoverageHeader")){
        	value=CoEdNoticeConstants.WorkersDisabiltyCoverageHeader;
        }else if(code.equalsIgnoreCase("WorkersDisabiltyCoverageValue")){
        	value=CoEdNoticeConstants.WorkersDisabiltyCoverageValue;
        }else if(code.equalsIgnoreCase("MeetsIncapacityrequirementsValue")){
        	value=CoEdNoticeConstants.MeetsIncapacityrequirementsValue;
        }else if(code.equalsIgnoreCase("MeetsIncapacityrequirementsHeader")){
        	value=CoEdNoticeConstants.MeetsIncapacityrequirementsHeader;
        }else if(code.equalsIgnoreCase("MeetsdisabilityrequirementsHeader")){
        	value=CoEdNoticeConstants.MeetsdisabilityrequirementsHeader;
        	
        }else if(code.equalsIgnoreCase("MeetsdisabilityrequirementsValue")){
        	value=CoEdNoticeConstants.MeetsdisabilityrequirementsValue;
        }
		//denied r2
        else if (code.equalsIgnoreCase("ACA0226Medicare")) {
			value = CoEdNoticeConstants.ACA0226MedicareValue;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value.replace("$X", date);
		}else if (code.equalsIgnoreCase("ACA0126Medicare")) {
			value = CoEdNoticeConstants.ACA0126MedicareHeader;
		}else if(code.equalsIgnoreCase("EmployerHealthInsuranceHeader")){
        	value=CoEdNoticeConstants.EmployerHealthInsuranceHeader;
        }else if(code.equalsIgnoreCase("EmployerHealthInsurancevalue")){
        	value=CoEdNoticeConstants.EmployerHealthInsurancevalue;
        	String name = indvIdNameMap.get(edEligibilityCargo.getTargetPersonId().toString());
        	value=value.replace("$X",name);
        }else if(code.equalsIgnoreCase("OutOfHouseholdHeader")){
			value = CoEdNoticeConstants.OutOfHouseholdHeader;
		}else if(code.equalsIgnoreCase("OutOfHouseholdValue")){
			value = CoEdNoticeConstants.OutOfHouseholdValue;
			String name = indvIdNameMap.get(edEligibilityCargo.getTargetPersonId().toString());
        	value=value.replace("$X",name);
		}else if(code.equalsIgnoreCase("ACA0121Withdrawn")){
			value = CoEdNoticeConstants.ACA0121WithdrawnHeader;
		}else if(code.equalsIgnoreCase("ACA0221Withdrawn")){
			value = CoEdNoticeConstants.ACA0221WithdrawnValue;
		}else if(code.equalsIgnoreCase("ACADisabilityClientIneligible")){
			value = CoEdNoticeConstants.ACADisabilityClientIneligibleHeader;
		}else if(code.equalsIgnoreCase("ACADisabilityClientIneligible")){
			value = CoEdNoticeConstants.ACADisabilityClientIneligible;
			String date=NOEMedicaid.getRecertDt(edEligibilityCargo.getCaseNum(), edEligibilityCargo.getEdgNum());
			value=value.replace("$X", date);
		}
		
        else {
			value = CoConstants.EMPTY_STRING;
		}
		return value;
	}

	private NoticeAttachment populateAttachment(HashMap<String, String> noticeFieldCodeDescriptionMap) {

		if (noticeFieldCodeDescriptionMap == null || noticeFieldCodeDescriptionMap.size() == 0) {
			noticeFieldCodeDescriptionMap = getNoticeFieldCodeDescriptionMap();
		}

		NoticeAttachment noticeAttachment = new NoticeAttachment();
		noticeAttachment.setSanfordHealthPlanNumber(noticeFieldCodeDescriptionMap.get(CoConstants.SHPNUM));
		noticeAttachment.setSanfordHealthPlanSite(noticeFieldCodeDescriptionMap.get(CoConstants.SHPSITE));
		noticeAttachment.setPhyAmt(noticeFieldCodeDescriptionMap.get(CoConstants.PHYAMT));
		noticeAttachment.setNurAmt(noticeFieldCodeDescriptionMap.get(CoConstants.NURAMT));
		noticeAttachment.setPaAmt(noticeFieldCodeDescriptionMap.get(CoConstants.PAAMT));
		noticeAttachment.setDruAmt(noticeFieldCodeDescriptionMap.get(CoConstants.DRUAMT));
		noticeAttachment.setSpiAmt(noticeFieldCodeDescriptionMap.get(CoConstants.SPIAMT));
		noticeAttachment.setSphAmt(noticeFieldCodeDescriptionMap.get(CoConstants.SPHAMT));
		noticeAttachment.setPhlAmt(noticeFieldCodeDescriptionMap.get(CoConstants.PHLAMT));
		noticeAttachment.setOcpAmt(noticeFieldCodeDescriptionMap.get(CoConstants.OCPAMT));
		noticeAttachment.setPsychAmt(noticeFieldCodeDescriptionMap.get(CoConstants.PSYCHAMT));
		noticeAttachment.setRurAmt(noticeFieldCodeDescriptionMap.get(CoConstants.RURAMT));
		noticeAttachment.setFedAmt(noticeFieldCodeDescriptionMap.get(CoConstants.FEDAMT));
		noticeAttachment.setPodAmt(noticeFieldCodeDescriptionMap.get(CoConstants.PODAMT));
		noticeAttachment.setEmrAmt(noticeFieldCodeDescriptionMap.get(CoConstants.EMRAMT));
		noticeAttachment.setHosAmt(noticeFieldCodeDescriptionMap.get(CoConstants.HOSAMT));
		return noticeAttachment;

	}

	*//**
	 * 
	 * @param edEligibilityCargos
	 * @return
	 *//*
	private List<EdEligibilityCargo> sortByBenefitStartDate(List<EdEligibilityCargo> edEligibilityCargos) {
		// sort on the basis of begin date starts
		if (edEligibilityCargos != null && edEligibilityCargos.size() > 0) {
			HashMap<EdEligibilityCargo, Timestamp> passedMap = new HashMap<EdEligibilityCargo, Timestamp>();
			List<EdEligibilityCargo> sortedEdEligibilityCargos = new ArrayList<EdEligibilityCargo>();

			for (EdEligibilityCargo eligibilityCargo : edEligibilityCargos) {
				passedMap.put(eligibilityCargo, eligibilityCargo.getBenefitStartDt());
			}
			List<EdEligibilityCargo> mapKeys = new ArrayList<EdEligibilityCargo>(passedMap.keySet());

			List<Timestamp> mapValues = new ArrayList<Timestamp>(passedMap.values());
			Collections.sort(mapValues);
			Iterator<Timestamp> valueIt = (Iterator<Timestamp>) mapValues.iterator();
			while (valueIt.hasNext()) {
				Object val = valueIt.next();
				Iterator<EdEligibilityCargo> keyIt = (Iterator<EdEligibilityCargo>) mapKeys.iterator();

				while (keyIt.hasNext()) {
					Object key = keyIt.next();
					Timestamp comp1 = passedMap.get(key);
					Timestamp comp2 = (Timestamp) val;
					if (comp1.equals(comp2)) {
						passedMap.remove(key);
						mapKeys.remove(key);
						sortedEdEligibilityCargos.add((EdEligibilityCargo) key);
						break;
					}
				}
			}
			passedMap = null;
			return sortedEdEligibilityCargos;
		}
		// sort on the basis of begin date ends
		return null;
	}

	private HouseholdGilReporting householdGilReportingAttachment(COCorrespondence coCorrespondence,MetaData metaData)	{
		String ishouseholdGilReporting="N";
		String householdSNAP="N";
		long householdEdgTraceId=0;
		HouseholdGilReporting householdGilReporting = new HouseholdGilReporting();
		householdGilReporting.setIsHouseholdGilReporting(ishouseholdGilReporting);
		EdEligFsBudgetCargo[] cargos;
		try {
			if (coCorrespondence.getCaseAppFlag() == CoConstants.CASE) { 
				householdEdgTraceId=checkSnapCase(coCorrespondence);
			}
			if(householdEdgTraceId!=0){
			cargos = coDAOServices.getEdEligFsBudgetdao(Long.valueOf(coCorrespondence.getCaseAppNumber()));
			if(null != cargos){
			for(EdEligFsBudgetCargo cargo : cargos){
				String val=cargo.getGrsTestRsltCd();
				if((CoConstants.FL).equals(val)){
					ishouseholdGilReporting=CoConstants.YES_STRING_Y;
					break;
				}
			}
			if((CoConstants.YES_STRING_Y).equals(ishouseholdGilReporting)){
				householdGilReporting.setSspLink(CoConstants.SSP_LINK);
				householdGilReporting.setIsHouseholdGilReporting(ishouseholdGilReporting);
				for(EdEligFsBudgetCargo cargo : cargos){
					if(householdEdgTraceId==(cargo.getEdgTraceId())){
						householdGilReporting.setGrossIncomeLimit(Double.toString(cargo.getGrsIncLimitAmt()));
					}
				}
			}
			}
			}
		}catch(Exception e)	{
			CoDebugger.debugException(e.getMessage(), e);
		}
	   return householdGilReporting;
	}
	private long checkSnapCase(COCorrespondence coCorrespondence){
		EdEligibilityCargo[] edEligibilityList;
		String caseNum=coCorrespondence.getCaseAppNumber();
		long householdEdgTraceId=0;
		Date today = new Date();
		int currMonth=today.getMonth()+1;
		try {
			edEligibilityList = (EdEligibilityCargo[]) coDAOServices.getEdEligibilityByCaseNumForSNAP(Long.valueOf(coCorrespondence.getCaseAppNumber()));
			if (null != edEligibilityList && edEligibilityList.length > 0){ 
				for (EdEligibilityCargo edEligibilityCargo : edEligibilityList) {
					if(caseNum.equals(Long.toString(edEligibilityCargo.getCaseNum()))&&(CoConstants.CHAR_N == edEligibilityCargo.getDeleteSw())){
						Date paymentBegDt=edEligibilityCargo.getPaymentBegDt();
						int paymentMonth=paymentBegDt.getMonth()+1;
						if(currMonth==paymentMonth){
							householdEdgTraceId=edEligibilityCargo.getEdgTraceId();
							return householdEdgTraceId;
						}
					}
				}
			}	
		}catch(Exception e)	{
			CoDebugger.debugException(e.getMessage(), e);
		}
		return householdEdgTraceId;
	}

	
	
	
	
	
}
*/