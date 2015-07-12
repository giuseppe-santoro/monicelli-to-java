grammar Monicelli;

@header { 
    package it.monicelli;
}

programma
	: (definizione_funzione)* funzione_principale (definizione_funzione)* 
	;

funzione_principale
	: LEI_HA_CLACSONATO istruzioni
	;
	
definizione_funzione
	: BLINDA_LA_SUPERCAZZOLA TIPO? identificatore definizione_parametri O_SCHERZIAMO istruzioni
	;

definizione_parametri
	: (CON definizione_parametro+)?
	;
	
definizione_parametro
	: variabile TIPO VIRGOLA?
	;
	
istruzioni
	: (istruzione | condizione_multipla) +
	;

istruzione
	: dichiarazione
	| assegnazione
	| lettura
	| scrittura
	| ciclo 
	| chiamata_funzione
	| ritorna_valore
	;

ritorna_valore
	: VAFFANZUM espressione? PUNTO_ESCLAMATIVO
	;

dichiarazione
	: VOGLIO variabile VIRGOLA TIPO ( come_fosse espressione )?
	;

assegnazione
	: variabile come_fosse espressione
	;
	
lettura
	: MI_PORGA variabile
	;

scrittura
	: espressione A_POSTERDATI
	;

ciclo
	: STUZZICA istruzione+ condizione_ciclo
	;

condizione_ciclo
	: E_BREMATURA_ANCHE_SE condizione
	;

condizione_multipla
	: CHE_COS_E ARTICOLO? IDENTIFICATORE PUNTO_INTERROGATIVO 
		condizione_multipla_primo_ramo istruzioni
		(condizione_multipla_ramo_intermedio istruzioni)*
		(condizione_multipla_ramo_finale istruzioni)?
		E_VELOCITA_DI_ESECUZIONE
	;

condizione_multipla_primo_ramo
	: operatore_condizione? espressione DUE_PUNTI
	;

condizione_multipla_ramo_intermedio
	: O_MAGARI operatore_condizione? espressione DUE_PUNTI
	;

condizione_multipla_ramo_finale
	: O_TARAPIA_TAPIOCO DUE_PUNTI
	;

espressione
	: chiamata_funzione
	| espressione piu espressione
	| espressione meno espressione
	| espressione per espressione
	| spostamento_bits
	| var_o_val
	;
	
spostamento_bits
	: var_o_val CON_SCAPPELLAMENTO_A direzione PER var_o_val
	;

chiamata_funzione
	: BREMATURATA_LA_SUPERCAZZOLA identificatore passaggio_parametri O_SCHERZIAMO;

passaggio_parametri
	: (CON passaggio_parametro+)?
	;
	
passaggio_parametro
	: espressione VIRGOLA?
	;

var_o_val
	: variabile
	| valore
	;

identificatore
	: IDENTIFICATORE;

variabile
	: ARTICOLO? IDENTIFICATORE
	;

valore
	: VALORE
	; 
	
condizione
	: variabile operatore_condizione espressione
	;

come_fosse
	: COME_FOSSE
	;
	
piu
	: PIU
	;
	
meno
	: MENO
	;

per
	: PER
	;
	
operatore_condizione
	: OPERATORE_CONDIZIONE
	;

direzione
	: DIREZIONE
	;

LEI_HA_CLACSONATO : 'Lei ha clacsonato';
BLINDA_LA_SUPERCAZZOLA : 'blinda la supercazzola';
O_SCHERZIAMO : 'o scherziamo?';
VAFFANZUM : 'vaffanzum';
CHE_COS_E : 'che cos\'è';
O_MAGARI : 'o magari';
O_TARAPIA_TAPIOCO : 'o tarapia tapioco';
E_VELOCITA_DI_ESECUZIONE : 'e velocità di esecuzione';
VIRGOLA : ',' ;	
VOGLIO : 'voglio';
CON_SCAPPELLAMENTO_A : 'con scappellamento a';
PER : 'per';
BREMATURATA_LA_SUPERCAZZOLA : 'brematurata la supercazzola' | 'prematurata la supercazzola';
TIPO : 'Necchi' | 'Mascetti' | 'Perozzi' | 'Melandri' | 'Sassaroli';
DIREZIONE : 'destra' | 'sinistra';
STUZZICA : 'stuzzica';
OPERATORE_CONDIZIONE : 'maggiore di' | 'minore di' | 'maggiore uguale a' | 'maggiore uguale di' | 'minore uguale a' | 'minore uguale di';
COME_FOSSE : 'come fosse' | 'come se fosse';
MI_PORGA : 'mi porga';
A_POSTERDATI : 'a posterdati';
E_BREMATURA_ANCHE_SE : 'e brematura anche, se' | 'e prematura anche, se';
PIU : 'più';
MENO : 'meno';
CON : 'con';
PUNTO_INTERROGATIVO : '?';
PUNTO_ESCLAMATIVO : '!';
DUE_PUNTI : ':';
VALORE : ('-'|'+')?[0-9]+('.'[0-9]+)? ;
ARTICOLO : 'il' | 'lo' | 'la' | 'i' | 'gli' | 'le' | 'un' | 'una' | 'dei' | 'delle' | 'l\'' | 'un\'';
IDENTIFICATORE : [a-zA-Z][a-zA-Z0-9]+;
	
COMMENT : 'bituma' (~'\n')* '\n' -> skip;
VIRGOLA_FINALE : ',\n' -> skip;
WS	:	(' '|'\r'|'\t'|'\n') -> skip ;
		
		
		
		
		
		
		
		
		
