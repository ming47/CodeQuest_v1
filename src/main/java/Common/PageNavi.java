package Common;
/**
 * 페이지 네비를 만들어주는 유틸
 * @author 문혁
 * @param index 현재 페이지
 * @param totalLength 보여줄 리스트의 전체 길이
 * @param recordCountPerPage 페이지당 보여줄 리스트의 개수
 * @param naviCountPePage 페이지에 보여줄 페이지 네비의 개수
 */
public class PageNavi {
	private int index;
	private int totalLength;
	private int recodeCountPerPage;
	private int naviCountPerPage;
	
	public PageNavi(int index, int totalLength, int recodeCountPerPage, int naviCountPerPage) {
		this.index = index;
		this.totalLength = totalLength;
		this.recodeCountPerPage = recodeCountPerPage;
		this.naviCountPerPage = naviCountPerPage;
	}
	
	public PageNavi(int index, int totalLength) {
		this.index = index;
		this.totalLength = totalLength;
		this.recodeCountPerPage = Statics.recordCountPerPage;
		this.naviCountPerPage = Statics.naviCountPerPage;
	}
	
	public int getStartNavi() {
		return (index - 1) / naviCountPerPage * naviCountPerPage + 1;
	}
	
	public boolean isFirst() {
		return getStartNavi() == 1;
	}
	
	public boolean isEnd() {
		return getEndNavi() == getIndexCount();
	}
	
	public int getEndNavi() {
		int endNavi = getStartNavi() + (naviCountPerPage - 1);
		
		return (endNavi > getIndexCount()) ? getIndexCount() : endNavi;
	}

	public int getIndex() {
		return index;
	}

	public int getIndexCount() {
		return (int) Math.ceil((double)totalLength / recodeCountPerPage);
	}
	
	public int getTotalLength() {
		return totalLength;
	}
	
	/**
	 * jsp에서 makePageNavi에게 값을 주기 위해 dto를 만드는 함수
	 * @return PageNaviDTO
	 */
	public PageNaviDTO generate() {
		return new PageNaviDTO(getStartNavi(), getEndNavi(), isFirst(), isEnd());
	}
	
	public static class PageNaviDTO {
		private int startNavi;
		private int endNavi;
		private boolean isFirst;
		private boolean isEnd;
		
		private PageNaviDTO(int startNavi, int endNavi, boolean isFirst, boolean isEnd) {
			this.startNavi = startNavi;
			this.endNavi = endNavi;
			this.isFirst = isFirst;
			this.isEnd = isEnd;
		}
		
		public int getStartNavi() {
			return startNavi;
		}
		
		public int getEndNavi() {
			return endNavi;
		}
		
		public boolean getIsFirst() {
			return isFirst;
		}
		
		public boolean getIsEnd() {
			return isEnd;
		}
	}
}
