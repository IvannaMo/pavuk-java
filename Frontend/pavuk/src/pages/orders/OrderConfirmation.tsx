import Navbar from "../../components/common/Navbar";
import OrderPreview from "../../components/orders/OrderPreview";
import Footer from "../../components/common/Footer";


function OrderConfirmation() {
  return (
    <>
      <Navbar userAccountLink={true} clothingItemsLink={true}></Navbar>
      <OrderPreview></OrderPreview>
      <Footer></Footer>
    </>
  );
}

export default OrderConfirmation;