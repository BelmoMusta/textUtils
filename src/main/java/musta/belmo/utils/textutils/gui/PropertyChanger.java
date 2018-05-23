package musta.belmo.utils.textutils.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertyChanger {
    public static void main(String[] args) {
        String mydata = "/**\n" +
                " * \n" +
                " */\n" +
                "package fr.cnav.www.ns.wsdl.rgcu.services.v1.mapping;\n" +
                "\n" +
                "import fr.cnav.rgcu.request.dvaloreg.C009;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.ActiviteNonSalariee;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.ActivitePrincipale;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.ActiviteSecondaire;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.Indicateur12;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.Situation;\n" +
                "import fr.msa.si.referentiel.carriere.carriere.dto.v1.SousTypeActivite;\n" +
                "\n" +
                "public class ActiviteNonSalarieeMapper {\n" +
                "\n" +
                "    public static ActiviteNonSalariee mapActiviteNonSalariee(final C009 pC009) {\n" +
                "        ActiviteNonSalariee lActiviteNonSalariee = null;\n" +
                "        if (pC009 != null) {\n" +
                "            lActiviteNonSalariee = new ActiviteNonSalariee();\n" +
                "            lActiviteNonSalariee.setCodeEmploi(MapperHelper.getValue(pC009.getC009001()));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setSousTypeActivite(SousTypeActivite.fromValue(MapperHelper.getValue(pC009.getC009002())));\n" +
                "            lActiviteNonSalariee.setSituation(Situation.fromValue(MapperHelper.getValue(pC009.getC009003())));\n" +
                "            lActiviteNonSalariee.setPartCoexploitation(MapperHelper.getValue(pC009.getC009007()));\n" +
                "            lActiviteNonSalariee.setType(MapperHelper.getValue(pC009.getC009010()));\n" +
                "\n" +
                "            lActiviteNonSalariee.setSurfacePondereeDOM(MapperHelper.getValueAsString(pC009.getC009011()));\n" +
                "            lActiviteNonSalariee.setNombreJours(MapperHelper.getValueAsString(pC009.getC009503()));\n" +
                "\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setIndicateurConventionne(Indicateur12.fromValue(MapperHelper.getValue(pC009.getC009006())));\n" +
                "            lActiviteNonSalariee.setAccordAssure(Indicateur12.fromValue(MapperHelper.getValue(pC009.getC009705())));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setActivitePrincipale(ActivitePrincipale.fromValue(MapperHelper.getValue(pC009.getC009004())));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setActiviteSecondaire(ActiviteSecondaire.fromValue(MapperHelper.getValue(pC009.getC009005())));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setIndicateurAnticipation(Indicateur12.fromValue(MapperHelper.getValue(pC009.getC009706())));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setIndicateurPresomption(Indicateur12.fromValue(MapperHelper.getValue(pC009.getC009707())));\n" +
                "            lActiviteNonSalariee\n" +
                "                    .setIndicateurDureeInterpretee(Indicateur12.fromValue(MapperHelper.getValue(pC009.getC009708())));\n" +
                "            lActiviteNonSalariee.setDateDebut(MapperHelper.getValue(pC009.getC009501()));\n" +
                "            lActiviteNonSalariee.setDateFin(MapperHelper.getValue(pC009.getC009502()));\n" +
                "        }\n" +
                "        return lActiviteNonSalariee;\n" +
                "    }\n" +
                "}\n";

        Scanner sc = new Scanner(mydata);
        Map<String, String> map = new HashMap<>();
        while (sc.hasNextLine()) {

            map(sc.nextLine(), map);

        }

        System.out.println(map);
    }

    public static void map(String mydata, Map<String, String> map) {

        Pattern patternSetter = Pattern.compile("set[a-zA-Z0-9]+\\(");
        Matcher matcherSetter = patternSetter.matcher(mydata);

        String key = null;
        String value = null;

        if (matcherSetter.find()) {
            key = matcherSetter.group(0);

        }
        mydata = mydata.replaceAll("MapperHelper\\.getValue[a-zA-Z]*+", "");
        Pattern patternGetter = Pattern.compile("get[a-zA-Z0-9]+");
        Matcher matcherGetter = patternGetter.matcher(mydata);

        if (matcherGetter.find()) {
            value = matcherGetter.group(0);
        }
        if (key != null) {
            map.put(key, value);
        }
    }
}
