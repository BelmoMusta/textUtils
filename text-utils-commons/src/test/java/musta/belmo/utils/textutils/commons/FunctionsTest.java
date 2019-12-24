package musta.belmo.utils.textutils.commons;

import musta.belmo.utils.textutils.commons.text.line.TextLinesUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FunctionsTest {
    @Test
    public void capitalizeEachWord() {

        String expected = "Aaa Bbb Ccc";
        String result = StringUtilities.capitalizeEachWord("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void changeCaseUpper() {
        String text = "aaa bbb ccc";
        String expected = "AAA BBB CCC";
        String result = StringUtilities.changeCase(text, StringUtilities.UPPER_CASE);
        Assert.assertEquals(expected, result);

    }

    @Test
    public void changeCaseLower() {
        String text = "AAA BBB CCC ÉÉÀ";
        String expected = "aaa bbb ccc ééà";
        String result = StringUtilities.changeCase(text, StringUtilities.LOWER_CASE);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void capitalize() {
        String expected = "Aaa bbb ccc";
        String result = StringUtilities.capitalize("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void capitalizeOneLetter() {
        String expected = "A";
        String result = StringUtilities.capitalize("a");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteEmptyLines() {
        String expected = "aaa \nbbb ccc";
        String result = StringUtilities.deleteEmptyLines("aaa \n\n\n\n\nbbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void camelCase() {
        String expected = "AaaBbbCcc";
        String result = StringUtilities.camelCase("aaa bbb ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void reduceWhiteSpaces() {
        String expected = "aaa\n\tbbb ccc";
        String result = StringUtilities.reduceWhiteSpaces("aaa\n\n\n\n\n\t\t\tbbb              ccc");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void delete() {
        String expected = "bbbccc";
        String result = TextLinesUtils.delete("aaabbbccc", "aaa");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getHighlights() {

        List<HighlightPosition> highlights
                = StringUtilities.getHighlights("aaabbbcccaannaa", "aa");

        HighlightPosition highlightPosition0 = new HighlightPosition(0, 2);
        HighlightPosition highlightPosition1 = new HighlightPosition(9, 11);
        HighlightPosition highlightPosition2 = new HighlightPosition(13, 15);

        Assert.assertTrue(highlights.contains(highlightPosition0));
        Assert.assertTrue(highlights.contains(highlightPosition1));
        Assert.assertTrue(highlights.contains(highlightPosition2));

    }

    @Test
    public void replaceAccentedLetters() {
        String expected = "aaaeeeeiiioouuAAAEEEE";
        String result = StringUtilities.replaceAccentedLetters("àâäêéëèîïìôòûùÀÂÄÉÈËÊ");
        //  Assert.assertEquals(expected, result);
    }

    @Test
    public void deleteSymbols() {
        String expected = "abcdefgh";
        String result = StringUtilities.deleteSymbols("abc-de(f+g_h");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void randomString() {
        String result = StringUtilities.randomString(10);
        Assert.assertEquals(10, result.length());
    }

    @Test
    public void testBase64EncodingAndDecoding() {
        String encode = StringUtilities.encode64("abc");
        String result = StringUtilities.decode64(encode);
        Assert.assertEquals("abc", result);
    }

    @Test
    public void testIndent() {
        String result = StringUtilities.indent("abc");
        Assert.assertEquals("\tabc\n", result);
    }

    @Test
    public void testSplitCamelcase() {
        String result = StringUtilities.splitCamelCase("aBcdeFghijKlmNo");
        Assert.assertEquals("a bcde fghij klm no", result);
    }

    @Test
    public void testUncamelcase() {
        String result = TextLinesUtils.deleteLines("a\nb\nc\nd\n", 1, 3);
        Assert.assertEquals("b\nd\n", result);
    }

    @Test
    public void testAddLines() {
        String result = TextLinesUtils.addLinesAtPositions("this is line 1 \n" +
                        "this is line 2\n" +
                        "this is line 3\n" +
                        "this is line 4\n" +
                        "this is line 5\n" +
                        "this is line 6",
                "2 add this line at position 2\n" +
                        "8 add this line at position 8\n" +
                        "0 add this line at position 0\n" +
                        "-1 add this line at position -1\n" +
                        "7 add this line at position 7\n" +
                        "5 add this line at position 5");

        System.out.println(result);
        //Assert.assertEquals("b\nd\n", result);
    }

    @Test
    public void testAddLinesUsingMap() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "line to be added in position 1");
        String result = TextLinesUtils.addLinesAtPositions("this is line 1 \n" +
                        "this is line 2\n" +
                        "this is line 3\n" +
                        "this is line 4\n" +
                        "this is line 5\n" +
                        "this is line 6",
                map);


        Assert.assertEquals("line to be added in position 1\n" +
                "this is line 1 \n" +
                "this is line 2\n" +
                "this is line 3\n" +
                "this is line 4\n" +
                "this is line 5\n" +
                "this is line 6", result);
    }

    @Test
    public void testJsonDiff() {
        String jsonA = "{\"typesSouscription\":[],\"connaissanceClient\":{\"client\":{\"situationProfessionnelle\":{\"situationActuelle\":{\"code\":\"AC\",\"libelle\":\"Actif(ve)\"},\"travailleurNonSalarie\":false,\"professionActuelle\":\"test\",\"categorieSocioProfessionnelle\":{\"code\":\"1\",\"libelle\":\"Agriculteur\"},\"secteurActivite\":{\"code\":\"T\",\"libelle\":\"Activités des ménages en tant qu'employeurs, en tant que producteurs de biens et services pour usage propre\"},\"informationsPPE\":{\"fonction\":{},\"paysExerce\":{}},\"lienPPE\":{\"fonction\":{},\"paysExerce\":{},\"natureLien\":{}},\"ppe\":false,\"lienAvecPPE\":false},\"etatCivil\":{\"prenom\":\"GHISLAINE\",\"nom\":\"SMITHE\",\"dateNaissance\":\"1975-06-03\",\"villeNaissance\":{\"code\":\"01142\",\"libelle\":\"Dagneux\"},\"departementNaissance\":{\"code\":\"01\",\"libelle\":\"Ain\"},\"pays\":{\"code\":\"FR\",\"libelle\":\"FRANCE\",\"typologiePays\":\"FRCE\"},\"nationalite\":{\"code\":\"FR\",\"libelle\":\"Française\"},\"civilite\":{\"code\":\"03\",\"libelle\":\"Mme\"},\"situationFamiliale\":{\"code\":\"CE\",\"libelle\":\"Célibataire\"},\"regimeMatrimonial\":{},\"personnesACharge\":[],\"nombrePersonnesACharge\":0},\"informationsFiscales\":{},\"identiteFiscaleHorsFranceEtUSA1\":{},\"identiteFiscaleHorsFranceEtUSA2\":{},\"residenceFiscaleHorsFranceUS\":false,\"residentFiscalOuCitoyenUs\":false,\"possessionNumeroIdentificationFiscale\":false,\"revenus\":[{\"typeRevenu\":{\"code\":\"SALA\",\"libelle\":\"Revenus professionnels\"},\"montant\":12121.00,\"afficherParDefaut\":0,\"ordre\":10},{\"typeRevenu\":{\"code\":\"FONC\",\"libelle\":\"Revenus fonciers\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":20},{\"typeRevenu\":{\"code\":\"REVE\",\"libelle\":\"Revenus de valeurs mobilières\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":30},{\"typeRevenu\":{\"code\":\"PENS\",\"libelle\":\"Pensions et retraites\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":40},{\"typeRevenu\":{\"code\":\"EMPR\",\"libelle\":\"Charge annuelle d'emprunt en cours\"},\"montant\":0,\"afficherParDefaut\":-1,\"ordre\":100}],\"patrimoine\":[{\"typePatrimoine\":{\"code\":\"RP\",\"libelle\":\"Résidence principale\",\"infobulle\":\"\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":10},{\"typePatrimoine\":{\"code\":\"RS\",\"libelle\":\"Résidence secondaire\"},\"montant\":12121.00,\"afficherParDefaut\":0,\"ordre\":20},{\"typePatrimoine\":{\"code\":\"IL\",\"libelle\":\"Investissement locatif\",\"infobulle\":\"\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":30},{\"typePatrimoine\":{\"code\":\"VM\",\"libelle\":\"Valeurs mobilières, Assurance Vie\",\"infobulle\":\"Indiquez dans cette rubrique, la valeur du patrimoine détenu en valeurs mobilières, compte titres, PEA, contrat d'assurance vie et contrat de capitalisation\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":40},{\"typePatrimoine\":{\"code\":\"LI\",\"libelle\":\"Liquidités, Livrets\",\"infobulle\":\"Indiquez dans cette rubrique, la valeur du patrimoine détenu en compte-courant, livrets, PEL\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":50},{\"typePatrimoine\":{\"code\":\"OA\",\"libelle\":\"Or, Collections, Objets d'art\",\"infobulle\":\"Concernant les collections, nous retiendrons ici les collections ayant une valeur marchande significative (certaines monnaies, objets anciens, timbres,….)\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":60},{\"typePatrimoine\":{\"code\":\"AP\",\"libelle\":\"Actifs professionnels\",\"infobulle\":\"\"},\"montant\":0,\"afficherParDefaut\":0,\"ordre\":70},{\"typePatrimoine\":{\"code\":\"EM\",\"libelle\":\"Capital restant dû sur les emprunts en cours\",\"infobulle\":\"\"},\"montant\":0,\"afficherParDefaut\":-1,\"ordre\":200}]},\"coordonnees\":{\"adresse\":{\"adressePrincipale\":\"Kergrac H\",\"adresseComplementaire\":\"\",\"codePostal\":\"29900\",\"ville\":{\"libelle\":\"Concarneau\"},\"pays\":{\"code\":\"FR\",\"libelle\":\"FRANCE\",\"typologiePays\":\"FRCE\"}},\"email\":\"test@test.fr\",\"telephoneMobile\":{\"indicatif\":\"0033\",\"numeroTelephone\":\"123456789\",\"codePays\":\"FR\"}},\"conjoint\":{\"etatCivil\":{\"villeNaissance\":{},\"departementNaissance\":{},\"pays\":{},\"nationalite\":{},\"civilite\":{}},\"situationProfessionnelle\":{\"situationActuelle\":{},\"travailleurNonSalarie\":false,\"categorieSocioProfessionnelle\":{},\"secteurActivite\":{},\"informationsPPE\":{\"fonction\":{},\"paysExerce\":{}},\"lienPPE\":{\"fonction\":{},\"paysExerce\":{},\"natureLien\":{}},\"ppe\":false,\"lienAvecPPE\":false},\"revenus\":[],\"patrimoine\":[]},\"foyer\":{\"revenus\":[],\"patrimoine\":[]},\"projetId\":7975,\"courtierId\":1234688,\"devoirConseilCourtier\":false,\"statut\":\"VALIDEE\",\"adresseResidenceFiscaleDifferente\":false,\"residenceFiscaleHorsFranceAmerique\":false,\"ppeClient\":false,\"ppeConjoint\":false,\"ppeLienFamilialeConjoint\":false,\"ppeLienFamiliale\":false},\"produit\":{\"code\":\"IC\",\"libelle\":\"INTENCIAL Liberalys Capi\",\"versementRib\":\"RIB_APICIL\",\"typeProduit\":\"CAPI\",\"description\":\"Contrat de capitalisation de droit français, multi-supports et multi-gestionnaires. Souple et digitalisé, ce contrat très accessible offre également une offre financière très diversifiée. Contrat disponible également pour les Personnes Morales. Dans ce cas, nous vous invitons à vous rapprocher de votre interlocuteur commercial.<br /><br />L'option PEA n'est pas encore accessible en mode digitale. La souscription du contrat de capitalisation avec option PEA, doit se faire sur un bulletin de souscription papier.\",\"propositionEligible\":true,\"typeDuree\":\"Fixée\",\"typesSouscription\":[{\"code\":\"002\",\"libelle\":\"Souscription pour majeur sous tutelle\",\"propSouscription\":false},{\"code\":\"004\",\"libelle\":\"Co-souscription démembrée \",\"propSouscription\":false},{\"code\":\"014\",\"libelle\":\"Souscription pour majeur sous curatelle\",\"propSouscription\":false},{\"code\":\"007\",\"libelle\":\"Non-résident fiscal\",\"propSouscription\":false},{\"code\":\"001\",\"libelle\":\"Co-souscription\",\"propSouscription\":false},{\"code\":\"006\",\"libelle\":\"Pacte adjoint à don manuel\",\"propSouscription\":false},{\"code\":\"008\",\"libelle\":\"Contrat en fonds propres\",\"propSouscription\":true}],\"afficherFraisEnCoursUc\":true,\"afficherFraisArbitrage\":true,\"afficherFraisToutVersement\":false,\"afficherFraisVersementInitial\":true,\"afficherFraisVerssementProgramme\":true,\"fraisFixSurArbitrage\":\"15 € +  \",\"vpRepartitionSpecifique\":true,\"tauxMinFEOpeVpPdtSouscription\":0.00,\"tauxMinFondsEurosOpeVersementInitial\":0.00,\"tauxMaxFESpecVpSouscription\":60.00,\"tauxMaxFondsEurosOpeVersementInitial\":60.00,\"tauxMaxFESpecVersInitialSouscription\":60.00,\"tauxMaxFondsEurosGlobalVersementInitial\":60.00,\"tauxMaxFondsEurosGlobalVPpdtSouscription\":60.00,\"fraisFixEndSurArbitrage\":\"% \",\"tauxFraisSurVersementParametrable\":false,\"tauxFraisSurArbitrageParametrable\":true,\"typeParametrageFraisEncoursUc\":\"SAISIE_PAR_TRANCHE\",\"tauxFraisGestionEuroParametrable\":false,\"tauxFraisGestionEuroflexParametrable\":true,\"tauxFraisSurVersement\":4.50000,\"tauxFraisSurArbitrage\":0.80000,\"tauxFraisSurEncoursUC\":1.00000,\"tauxFraisGestionEuroMin\":0.60,\"tauxFraisGestionEuroMax\":1.00000,\"tauxFraisSurGestionEuro\":1.00000,\"tauxFraisSurGestionEuroSpecifique\":4.00000,\"typeParametrageTauxGestionEuro\":\"NON_SAISISSABLE\",\"autoriseLaDerogationDeFrais\":true,\"montantMinimumParSupport\":50.00,\"tauxCompagnieVIValiorise\":false,\"fraisTaux\":{\"borneGlobal\":{\"min\":0.00000,\"max\":4.50000},\"borneCompagnie\":{\"min\":0,\"max\":0.00000},\"borneApporteur\":{\"min\":0,\"max\":4.50000},\"tauxApporteur\":4.50000,\"tauxCompagnie\":0.00000},\"derogationToutePartVI\":true,\"derogationToutePartVP\":true,\"derogationFraisVC\":true,\"tauxVersementCompagnie\":0.00000},\"profilGestion\":{\"code\":\"LIBL\",\"codeApicil\":\"LIBL\",\"libelle\":\"Gestion Libre\",\"surtauxMax\":0,\"versementMin\":{\"versementInitialMinimum\":1000.00,\"minVersementProgrammeMensuel\":100.00,\"minVersementProgrammeTrimestriel\":300.00,\"minVersementProgrammeSemestriel\":600.00,\"minVersementProgrammeAnnuel\":600.00},\"surtauxEncours\":0,\"dda\":0},\"souscription\":{\"versementInitial\":{\"montant\":400000.00,\"tauxDerogatoire\":3.00000,\"reponsesSupportStructure\":[],\"fraisPartTaux\":{\"apporteur\":3.00000,\"compagnie\":0.00000},\"payeur\":{\"lienSouscripteur\":{}},\"portefeuille\":[{\"isinCode\":\"EURO0000FEFF\",\"repartition\":0,\"libelle\":\"APICIL EURO GARANTI\",\"typeSupport\":\"FONDS_EURO\",\"emetteur\":\"\",\"dateExpiration\":\"2099-12-31\",\"dateExpirationSouscription\":\"2099-12-31\",\"lienDic\":true,\"fondsEuroSpecifique\":false,\"specifique\":false,\"urlExterne\":\"http://hub-recette3.partenaire-epargne.apicil.com/referentiel/support/EURO0000FEFF/pdf\"},{\"isinCode\":\"EFLEX0000001\",\"repartition\":0,\"libelle\":\"APICIL EUROFLEX\",\"typeSupport\":\"FONDS_EURO\",\"emetteur\":\"\",\"dateExpiration\":\"2099-12-31\",\"dateExpirationSouscription\":\"2099-12-31\",\"lienDic\":true,\"fondsEuroSpecifique\":true,\"specifique\":false,\"urlExterne\":\"http://hub-recette3.partenaire-epargne.apicil.com/referentiel/support/EFLEX0000001/pdf\"},{\"isinCode\":\"FR0011153014\",\"repartition\":100.00,\"libelle\":\"Ginjer Actifs 360 (A)\",\"typeSupport\":\"AUTRE\",\"dateExpiration\":\"2099-12-31\",\"dateExpirationSouscription\":\"2099-12-31\",\"lienDic\":true,\"fondsEuroSpecifique\":false,\"specifique\":false,\"urlExterne\":\"http://lsf.europerformance.fr/LSF/Document.do?id=courtagesystemes&isin=FR0011153014&dici=hight&lang=fr\"}],\"modePaiement\":\"C\",\"origineDesFonds\":{\"ofAutresPlacements\":400000.00}},\"dureeContrat\":\"Déterminée\",\"precisionDureeContrat\":2,\"fraisSurContrat\":{\"tauxFraisSurEncoursUC\":1.00000,\"totalTauxFraisSurEncoursUC\":1.00000,\"tauxFraisSurArbitrage\":0.20000,\"tauxFraisGestionEncoursEuro\":1.00000,\"tauxMaxFraisSurTousTypesVersements\":4.50000},\"versementProgramme\":{\"fraisPartTaux\":{\"apporteur\":4.50000,\"compagnie\":0.00000},\"portefeuille\":[{\"isinCode\":\"EURO0000FEFF\",\"repartition\":0,\"libelle\":\"APICIL EURO GARANTI\",\"typeSupport\":\"FONDS_EURO\",\"emetteur\":\"\",\"dateExpiration\":\"2099-12-31\",\"dateExpirationSouscription\":\"2099-12-31\",\"lienDic\":true,\"fondsEuroSpecifique\":false,\"specifique\":false,\"urlExterne\":\"http://hub-recette3.partenaire-epargne.apicil.com/referentiel/support/EURO0000FEFF/pdf\"},{\"isinCode\":\"EFLEX0000001\",\"repartition\":0,\"libelle\":\"APICIL EUROFLEX\",\"typeSupport\":\"FONDS_EURO\",\"emetteur\":\"\",\"dateExpiration\":\"2099-12-31\",\"dateExpirationSouscription\":\"2099-12-31\",\"lienDic\":true,\"fondsEuroSpecifique\":true,\"specifique\":false,\"urlExterne\":\"http://hub-recette3.partenaire-epargne.apicil.com/referentiel/support/EFLEX0000001/pdf\"}]},\"clauseBeneficiaire\":{},\"objectifInvestissement\":{\"code\":\"OBJ4\",\"libelle\":\"Épargner pour réaliser un projet\"},\"numero\":22588,\"statut\":\"011\",\"ppe\":\"NPPE\",\"dateSouscriptionOuProposition\":\"2019-12-24\",\"projetId\":7975,\"fraisStandardVersement\":4.50000,\"fraisStandardArbitrage\":0.80000,\"estVersementProgramme\":false,\"dateExpiration\":\"2020-01-08\",\"statutProposition\":\"PROPOSEE\",\"procedure\":{\"adresseOrganisationDto\":{\"nomOrga\":\"APICIL Epargne\",\"adresseOrga1\":\"Tour de Lyon\",\"adresseOrga2\":\"185 rue de Bercy\",\"cpOrga\":\"75579\",\"villeOrga\":\"Paris cedex 12\"},\"versementReglementDestinataire\":\"APICIL EPARGNE\"},\"patrimoineTotal\":\"0 € à 100 000 €\",\"revenusAnnualsFoyer\":\"0 € à 30 000 €\"},\"courtier\":{\"id\":1234688,\"nom\":\"AVJXLRIB\",\"prenom\":\"Jbvumhq\"},\"modeSignature\":\"ELECTRONIQUE\",\"projetId\":7975,\"projetCloneId\":7969,\"affaireAbandonner\":false,\"dejaRenoncer\":true,\"dateProposition\":\"2019-12-24\",\"dureeProposition\":15,\"dateValidite\":\"2020-01-08\",\"verificationAutomatiqueRIB\":true}";

        String jsonB = "{ \n" +
                "        \"src\": \"Image/Sun.png\",\n" +
                "        \"name\": \"sun2\",\n" +
                "        \"hOffset\": 270,\n" +
                "        \"vOffset\": 260,\n" +
                "        \"alignment\": \"center\"\n" +
                "    }";

        String jsonDiffSymetrie = StringUtilities.jsonDiff(jsonA, jsonA);
        String jsonDiff = StringUtilities.jsonDiff(jsonA, jsonB);
        JSONObject empty = new JSONObject();
        Assert.assertEquals(empty.toString(),jsonDiffSymetrie);
        System.out.println(jsonDiff);
    }
}