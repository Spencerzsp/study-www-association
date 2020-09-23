
///////////////////////////////////////////////////////////////////////////
// Loader
$(document).ready(function () {
    setTimeout(() => {
        $(".loading").fadeToggle(100);
    }, 500); // hide delay when page load
});
///////////////////////////////////////////////////////////////////////////
// Go Back
$(".goBack").click(function(){
    window.history.back();
});

///////////////////////////////////////////////////////////////////////////
// Sidebar 
$(".toggleSidebar").click(function(){
    $(".sidebarWrapper").fadeToggle(200);
    if($("body").hasClass("sidebarActive")){
        $("body").removeClass("sidebarActive");
    }
    else{
        $("body").addClass("sidebarActive");
    }
    if($(".sidebarWrapper .sidebar").hasClass("is-active")){
        $(".sidebarWrapper .sidebar").removeClass("is-active");
        $(".sidebarWrapper .sidebar").addClass("is-passive");
    }
    else{
        $(".sidebarWrapper .sidebar").addClass("is-active");
    }
});

$(".toggleSearchbox").click(function(){
    $(".searchBox").fadeToggle(200);

});
