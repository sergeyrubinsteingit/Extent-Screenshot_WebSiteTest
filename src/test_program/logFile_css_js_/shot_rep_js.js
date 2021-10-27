// Opens selected image in new window

function selected_Img(elem_id_){
	
	var imgSrc_ = document.getElementById(elem_id_).getElementsByTagName("img")[0].src; //Finds screenshot's src inside the page's icon div
	var linkUrl_ = document.getElementById(elem_id_).getElementsByClassName("pageURL_")[0].innerHTML; // Finds an URL of the page included in page's icon div

	let the_div_ = document.getElementById("enlarged_img_div"); // Container for an enlarged screenshot
	let the_button_ = document.getElementById("enlarged_img_butt"); // A button closing the window (container)
	
	document.getElementById("enlarged_img_img").src = imgSrc_; // Src for enlarged screenshot
	document.getElementById("enlarged_img_link_div").innerHTML = "<a href='" + linkUrl_ + "' target='_blank'>Open this page in browser</a>";
	
	the_div_.style.display = "block";

	the_button_.addEventListener("click", function(){
		the_div_.style.display = "none";
	}); //eoinside function
}//eof


// The functions below set height and width for divs and images contained by those.

const container_w_ = screen.availWidth * .9 + "px";
const container_h_ = screen.availHeight * .5 + "px";

function container_size(){
	
	document.getElementById("container_div").style.width = container_w_;
	document.getElementById("container_div").style.height = container_h_;

}//eof

function inner_div_size(){
	
	document.getElementsByClassName("img_Div").style.width = container_w_ * .8 + "px";
	document.getElementsByClassName("img_Div").style.height = container_h_ * .8 + "px";

}//eof

function img_size(){
	// document.getElementsByTagName("img").style.width = container_w_ / 5.2 + "px";
	// document.getElementsByTagName("img").style.width = 100%;
	// document.getElementsByTagName("img").style.height = "auto";
}//eof

function size_functions(){
	container_size();
	inner_div_size();
	// img_size();
}//eof
