import constants from "src/common/constants"
import styled from "styled-components"
import SystemForm from "./SystemForm"

const Container = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    flex: 1;
    height: 100%;
    gap: 1rem;
`

const ContentWrapper = styled.div`
    max-width: ${constants.ui.maxWidth};
    width: 100%;
`


const NewSimulationPage = () => {
    return (
        <Container>
            <h1>Create new simulation</h1>
            <ContentWrapper>
                <SystemForm/>
            </ContentWrapper>
        </Container>
    )
}

export default NewSimulationPage