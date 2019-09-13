package parsers;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppData {

	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("create_date")
	@Expose
	private String createDate;
	@SerializedName("send_date")
	@Expose
	private String sendDate;
	@SerializedName("state_as_text")
	@Expose
	private String stateAsText;
	@SerializedName("reason_declined")
	@Expose
	private String reasonDeclined;
	@SerializedName("contract_number")
	@Expose
	private String contractNumber;
	@SerializedName("base_sum")
	@Expose
	private String baseSum;
	@SerializedName("sum")
	@Expose
	private String sum;
	@SerializedName("period")
	@Expose
	private int period;
	@SerializedName("percent")
	@Expose
	private String percent;
	@SerializedName("product")
	@Expose
	private String product;
	@SerializedName("start_date")
	@Expose
	private String startDate;
	@SerializedName("return_date")
	@Expose
	private String returnDate;
	@SerializedName("insurance_status")
	@Expose
	private int insuranceStatus;
	@SerializedName("gid")
	@Expose
	private String gid;
	@SerializedName("from")
	@Expose
	private String from;
	@SerializedName("offers")
	@Expose
	private String offers;
	@SerializedName("photos")
	@Expose
	private List<Object> photos = null;
	@SerializedName("dops")
	@Expose
	private String dops;
	@SerializedName("state")
	@Expose
	private String state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getStateAsText() {
		return stateAsText;
	}

	public void setStateAsText(String stateAsText) {
		this.stateAsText = stateAsText;
	}

	public String getReasonDeclined() {
		return reasonDeclined;
	}

	public void setReasonDeclined(String reasonDeclined) {
		this.reasonDeclined = reasonDeclined;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getBaseSum() {
		return baseSum;
	}

	public void setBaseSum(String baseSum) {
		this.baseSum = baseSum;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public int getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(int insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public List<Object> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Object> photos) {
		this.photos = photos;
	}

	public String getDops() {
		return dops;
	}

	public void setDops(String dops) {
		this.dops = dops;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

