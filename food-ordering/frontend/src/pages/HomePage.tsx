import landing from "../assets/landing.png";
import  appDownloadImage from "../assets/appDownload.png";
export   default  function HomePage(){
    return (
        <div className="flex flex-col gap-12">
            <div className={"bg-white rounded-lg shadow-md py-8 flex flex-col gap-5 text-center -mt-16"}>
                <h1 className={"text-5xl font-bold tracking-tight text-orange-500"}>
                    Tuck into a takeaway today
                </h1>
                <span className={"text-xl"}>Food is just a click away!</span>
            </div>
            <div className={"grid md:grid-cols-2 gap-5"}>
                <img src={landing}
                     alt={"landing image"}
                />
                <div className={"flex flex-col items-center justify-center gap-4 text-center"}>
                    <span className={"font-bold text-3xl tracking-tight"}>
                        Order takeaways faster!
                    </span>
                    <span>
                        Download the app now for fast and easy takeaway ordering.
                    </span>
                    <img src={appDownloadImage}
                         alt={"app download image"}/>
                </div>
            </div>
        </div>
        );
}