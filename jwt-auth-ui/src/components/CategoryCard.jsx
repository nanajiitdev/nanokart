function CategoryCard({

    title,

    icon,

    onClick

}) {

    return (

        <div className="col-lg-2 col-md-3 col-sm-4 col-6 mb-4">

            <div
                className="card shadow-sm border-0 h-100 category-card"
                onClick={onClick}
                style={{
                    cursor: "pointer",
                    transition: "0.3s"
                }}
            >

                <div className="card-body text-center">

                    <div
                        style={{
                            fontSize: "55px"
                        }}
                    >
                        {icon}
                    </div>

                    <h6 className="mt-3 fw-bold">

                        {title}

                    </h6>

                </div>

            </div>

        </div>

    );

}

export default CategoryCard;