/**
 * @author Xi Gong
 * Div is used to capture the division information of the teams.
 * There are six divisions in NBA league:
 * PA - pacific division (LAC, PHX, SAC, LAL, GSW) 
 * NW - Northwest division (DEN, POR, OKC, UTA, MIN)
 * SW - Southwest division (DAL, SAN, HOU, NOP, MEM)
 * SE - Southeast division (ORL, MIA, CHA, WAS, ATL)
 * AT - Atlantic division ()
 * CT - Central division ()
 */
public enum Div{
	PA("PA"), 
	NW("NW"), 
	SW("SW"), 
	SE("SE"), 
	AT("AT"), 
	CT("CT");
	
	private final String value;
	Div(final String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return getValue();
	}
	
}
