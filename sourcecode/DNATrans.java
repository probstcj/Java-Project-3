/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.util.Scanner;

/**
 *
 * @author probs
 */
public class DNATrans {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
            Scanner stdin = new Scanner(System.in);
            System.out.println("Please enter a new DNA strand or press Ctrl-C to end");
            String strand = stdin.nextLine();
            try{
                System.out.println("RNA Strand: " + transcript(strand));
                System.out.println("Protein: " + translate(strand));
            }
            catch(IllegalArgumentException e){
                System.out.println("DNA strand is invalid, please use in the following formats:");
                System.out.println("5' TTATATATACGCGCG 3'");
                System.out.println("or");
                System.out.println("3' TATACGAGCGATCGA 5'");
            }
        }
    }
    public static String transcript (String strand) throws IllegalArgumentException{
        if (strand.indexOf("5")==0 && strand.indexOf("3") == strand.length()-2) {
            // 5' to 3'
            StringBuilder rna = new StringBuilder();
            rna.append("3' ");
            for (int i = 3; i < strand.length()-3; i++) {
                switch (strand.charAt(i)) {
                    case 'A':
                        rna.append("U");
                        break;
                    case 'T':
                        rna.append("A");
                        break;
                    case 'C':
                        rna.append("G");
                        break;
                    case 'G':
                        rna.append("C");
                        break;
                    default:
                        throw new IllegalArgumentException("Template strand does not contain only A, T, C, and G");
                }
            }
            rna.append(" 5'");
            return rna.toString();
        }
        else if(strand.indexOf("3")==0 && strand.indexOf("5") == strand.length()-2){
            // 3' to 5'
            StringBuilder rna = new StringBuilder();
            rna.append("5' ");
            for (int i = 3; i < strand.length()-3; i++) {
                switch (strand.charAt(i)) {
                    case 'A':
                        rna.append("U");
                        break;
                    case 'T':
                        rna.append("A");
                        break;
                    case 'C':
                        rna.append("G");
                        break;
                    case 'G':
                        rna.append("C");
                        break;
                    default:
                        throw new IllegalArgumentException("Template strand does not contain only A, T, C, and G");
                }
            }
            rna.append(" 3'");
            return rna.toString();
        }
        else{
            throw new IllegalArgumentException("Template strand does not start at the correct 5' or 3'");
        }
    }
    
    public static String translate (String strand) throws IllegalArgumentException{
        String rnaStrand = transcript(strand);
        if (rnaStrand.indexOf("5")==0 && rnaStrand.indexOf("3") == rnaStrand.length()-2) {
            // Forwards
            int AUGindex = rnaStrand.indexOf("AUG");
            StringBuilder polyPeptide = new StringBuilder();
            for (int i = AUGindex; i < rnaStrand.length() && i>0; i+=3) {
                StringBuilder codon = new StringBuilder();
                if(i<rnaStrand.length()-2){
                    codon.append(rnaStrand.charAt(i));
                    codon.append(rnaStrand.charAt(i+1));
                    codon.append(rnaStrand.charAt(i+2));
                }
                switch(codon.toString()){
                    case "UUU":
                    case "UUC": polyPeptide.append("Phe-");
                        break;
                    case "UUA":
                    case "UUG":
                    case "CUU": 
                    case "CUC":
                    case "CUA":
                    case "CUG": polyPeptide.append("Leu-");
                        break;
                    case "AUU":
                    case "AUC":
                    case "AUA": polyPeptide.append("Ile-");
                        break;
                    case "AUG": polyPeptide.append("Met-");
                        break;
                    case "GUU": 
                    case "GUC":
                    case "GUA":
                    case "GUG": polyPeptide.append("Val-");
                        break;
                    case "UCU":
                    case "UCC":
                    case "UCA":
                    case "UCG": polyPeptide.append("Ser-");
                        break;
                    case "CCU": 
                    case "CCC":
                    case "CCA":
                    case "CCG": polyPeptide.append("Pro-");
                        break;
                    case "ACU":
                    case "ACC":
                    case "ACA":
                    case "ACG": polyPeptide.append("Thr-");
                        break;
                    case "GCU":
                    case "GCC":
                    case "GCA":
                    case "GCG": polyPeptide.append("Ala-");
                        break;
                    case "UAU":
                    case "UAC": polyPeptide.append("Tyr-");
                        break;
                    case "UAA": 
                    case "UAG": polyPeptide.append("Stop");
                        i = 999;
                        break;
                    case "CAU":
                    case "CAC": polyPeptide.append("His-");
                        break;
                    case "CAA":
                    case "CAG": polyPeptide.append("Gln-");
                        break;
                    case "AAU":
                    case "AAC": polyPeptide.append("Asn-");
                        break;
                    case "AAA":
                    case "AAG": polyPeptide.append("Lys-");
                        break;
                    case "GAU": 
                    case "GAC": polyPeptide.append("Asp-");
                        break;
                    case "GAA":
                    case "GAG": polyPeptide.append("Glu-");
                        break;
                    case "UGU":
                    case "UGC": polyPeptide.append("Cys-");
                        break;
                    case "UGA": polyPeptide.append("Stop");
                        i = 999;
                        break;
                    case "UGG": polyPeptide.append("Trp-");
                        break;
                    case "CGU":
                    case "CGC":
                    case "CGA":
                    case "CGG": polyPeptide.append("Arg-");
                        break;
                    case "AGU":
                    case "AGC": polyPeptide.append("Ser-");
                        break;
                    case "AGA":
                    case "AGG": polyPeptide.append("Arg-");
                        break;
                    case "GGU":
                    case "GGC":
                    case "GGA":
                    case "GGG": polyPeptide.append("Gly-");
                        break;
                }
            }
            return polyPeptide.toString();
        }
        else if(rnaStrand.indexOf("3")==0 && rnaStrand.indexOf("5") == rnaStrand.length()-2){
            // Backwards
            int GUAindex = rnaStrand.lastIndexOf("GUA");
            StringBuilder polyPeptide = new StringBuilder();
            for (int i = GUAindex; i > 2; i-=3) {
                StringBuilder codon = new StringBuilder();
                if(i<rnaStrand.length()){
                    codon.append(rnaStrand.charAt(i));
                    codon.append(rnaStrand.charAt(i+1));
                    codon.append(rnaStrand.charAt(i+2));
                }
                switch(codon.toString()){
                    case "UUU":
                    case "CUU": polyPeptide.append("Phe-");
                        break;
                    case "AUU":
                    case "GUU":
                    case "UUC": 
                    case "CUC":
                    case "AUC":
                    case "GUC": polyPeptide.append("Leu-");
                        break;
                    case "UUA":
                    case "CUA":
                    case "AUA": polyPeptide.append("Ile-");
                        break;
                    case "GUA": polyPeptide.append("Met-");
                        break;
                    case "UUG": 
                    case "CUG":
                    case "AUG":
                    case "GUG": polyPeptide.append("Val-");
                        break;
                    case "UCU":
                    case "CCU":
                    case "ACU":
                    case "GCU": polyPeptide.append("Ser-");
                        break;
                    case "UCC": 
                    case "CCC":
                    case "ACC":
                    case "GCC": polyPeptide.append("Pro-");
                        break;
                    case "UCA":
                    case "CCA":
                    case "ACA":
                    case "GCA": polyPeptide.append("Thr-");
                        break;
                    case "UCG":
                    case "CCG":
                    case "ACG":
                    case "GCG": polyPeptide.append("Ala-");
                        break;
                    case "UAU":
                    case "CAU": polyPeptide.append("Tyr-");
                        break;
                    case "AAU": 
                    case "GAU": polyPeptide.append("Stop");
                        i = -4;
                        break;
                    case "UAC":
                    case "CAC": polyPeptide.append("His-");
                        break;
                    case "AAC":
                    case "GAC": polyPeptide.append("Gln-");
                        break;
                    case "UAA":
                    case "CAA": polyPeptide.append("Asn-");
                        break;
                    case "AAA":
                    case "GAA": polyPeptide.append("Lys-");
                        break;
                    case "UAG": 
                    case "CAG": polyPeptide.append("Asp-");
                        break;
                    case "AAG":
                    case "GAG": polyPeptide.append("Glu-");
                        break;
                    case "UGU":
                    case "CGU": polyPeptide.append("Cys-");
                        break;
                    case "AGU": polyPeptide.append("Stop");
                        i = -4;
                        break;
                    case "GGU": polyPeptide.append("Trp-");
                        break;
                    case "UGC":
                    case "CGC":
                    case "AGC":
                    case "GGC": polyPeptide.append("Arg-");
                        break;
                    case "UGA":
                    case "CGA": polyPeptide.append("Ser-");
                        break;
                    case "AGA":
                    case "GGA": polyPeptide.append("Arg-");
                        break;
                    case "UGG":
                    case "CGG":
                    case "AGG":
                    case "GGG": polyPeptide.append("Gly-");
                        break;
                }
            }
            return polyPeptide.toString();
        }
        else{
            throw new IllegalArgumentException("5' and 3' must be contained on the first and last characters");
        }
    }
}
