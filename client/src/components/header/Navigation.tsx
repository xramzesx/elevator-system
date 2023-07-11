
import styled from "styled-components"
import constants from "../../common/constants"
import SystemForm from "./SystemForm"

const Header = styled.header`
    background-color: ${constants.ui.colors.background};
    color: ${constants.ui.colors.foreground};
    /* color: white; */
    display: flex;
    align-items: flex-end;
    justify-content: center;
    min-height: 7rem;
`

const Navbar = styled.nav`
    padding: 1rem 2rem;
    width: 100%;
    max-width: ${constants.ui.maxWidth};
    display: flex;
    flex-direction: column;
    justify-content: flex-end;

`

const Navigation = () => {
    
    return (
        <Header>
            <Navbar>
                <h1>ELEVATOR</h1> 
                <SystemForm />               
            </Navbar>
        </Header>
    )
}

export default Navigation