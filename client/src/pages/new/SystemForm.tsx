import { Button, FormControl, MenuItem, Paper, Select, SelectChangeEvent, TextField } from "@mui/material"

import styled from "styled-components"
import constants from "src/common/constants"
import { ChangeEvent, FormEvent, useState } from "react"
import { ElevatorFactoryVariant, ElevatorPickupStrategy, ElevatorSystemVariant, SimulationConfig } from "src/common/types"
import { useNavigate } from "react-router-dom"


const Form = styled.form`
    padding: 1rem;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    width: 100%;
    align-items: flex-end;
`

const FieldWrapper = styled.div`
    display: flex;
    flex:1;
    width: 100%;
    gap: 1rem;

    @media(max-width: ${constants.ui.mobileWidth}) {
        flex-direction: column;
    }
`

const Field = styled.fieldset<{$border?: boolean}>`
    flex: 1;
    display: flex;
    flex-direction: column;
    gap: 1rem;

    padding: 1rem;
    border-radius: .5rem;
    ${props => props.$border ? "" : "border: none;"}
`

const FieldLegend = styled.legend`
    font-weight: 500;
    font-size: 1.5rem;
    padding-bottom: 1rem;
    padding: .5rem;
`

const FormFooter = styled.div`
    display: flex;
    align-items: space-between;
    width: 100%;
`

const ErrorMessage = styled.div`
    min-height: 2rem;
    color: red;
    font-weight: 600;
    width: 100%;
    /* text-align: center; */
    padding-left: 1rem;
`

const SystemForm = () => {
    const navigate = useNavigate()

    const [system, setSystem] = useState(ElevatorSystemVariant.SCAN)
    const [factory, setFactory] = useState(ElevatorFactoryVariant.DEFAULT)
    const [strategy, setStrategy] = useState(ElevatorPickupStrategy.DEFAULT)
    
    const [elevatorCount, setElevatorCount] = useState(3)
    const [highestFloor, setHighestFloor] = useState(10)
    const [lowestFloor, setLowestFloor] = useState(-2)
    const [delay, setDelay] = useState(constants.elevator.minDelay)

    const [isLoading, setIsLoading] = useState(false)

    const [errorMessage, setErrorMessage] = useState("")

    //// HANDLERS ////

    const handleSystemChange = (event: SelectChangeEvent<ElevatorSystemVariant>) => {
        setSystem(event.target.value as ElevatorSystemVariant)
    }
    
    const handleFactoryChange = (event: SelectChangeEvent<ElevatorFactoryVariant>) => {
        setFactory(event.target.value as ElevatorFactoryVariant)
    }
    
    const handleStrategyChange = (event: SelectChangeEvent<ElevatorPickupStrategy>) => {
        setStrategy(event.target.value as ElevatorPickupStrategy)
    }

   
    const handleElevatorCountChange = (event: ChangeEvent<HTMLInputElement>) => {
        const elevators = event.target.value as unknown as number

        if (elevators >= constants.elevator.count.min || elevators <= constants.elevator.count.max)
            setElevatorCount(elevators)
    }
   
    const handleHighestFloorChange = (event: ChangeEvent<HTMLInputElement>) => {
        const floor = event.target.value as unknown as number
                
        setHighestFloor(floor)
    }
   
    const handleLowestFloorChange = (event: ChangeEvent<HTMLInputElement>) => {
        const floor = event.target.value as unknown as number
        setLowestFloor(floor)
    }

    const handleDelayChange = (event: ChangeEvent<HTMLInputElement>) => {
        const delay = event.target.value as unknown as number
        setDelay(delay)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        setErrorMessage("")

        /// VALIDATION ///

        if ( elevatorCount < constants.elevator.count.min || elevatorCount > constants.elevator.count.max ) {
            setErrorMessage("Elevator must be in range [1,16]")
            return
        }

        if (highestFloor <= lowestFloor) {
            setErrorMessage("Highest floor must be higher than lowest floor")
            return;
        }

        if (delay < constants.elevator.minDelay) {
            setErrorMessage(`Delay can't be below ${constants.elevator.minDelay}ms`)
            return;
        }

        /// SEND REQUEST ///

        const config : SimulationConfig = {
            systemVariant : system,
            factoryVariant: factory,
            strategyVariant: strategy,
            highestFloor,
            lowestFloor,
            elevatorCount,
            delay
        }
        setIsLoading(true)
        const response = await fetch(
            new URL("/simulation", constants.api.url), {
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body : JSON.stringify(config)    
            })
        
        setIsLoading(false)

        if (!response.ok) {
            
        }

        const { simulationId } = await response.json()
        
        navigate(`/simulation/${simulationId}`)
    }

    //// RENDER ////

    return (
        <Paper>
            <Form onSubmit={handleSubmit}>
                <FieldWrapper>
                    <Field>
                        <FieldLegend>General</FieldLegend>
                        <Select
                            value={system}
                            onChange={handleSystemChange}
                        >
                            <MenuItem value={ElevatorSystemVariant.SCAN}>SCAN System</MenuItem>
                            <MenuItem value={ElevatorSystemVariant.FCFS}>FCFS System</MenuItem>
                            <MenuItem value={ElevatorSystemVariant.CUSTOM}>Custom System</MenuItem>
                        </Select>
                        <TextField 
                            value={elevatorCount} 
                            inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }} 
                            label="Elevators" 
                            onChange={handleElevatorCountChange}
                        />
                        <TextField 
                            value={delay} 
                            inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }} 
                            label="Delay (ms)" 
                            onChange={handleDelayChange}
                        />
                        <TextField 
                            value={highestFloor} 
                            inputProps={{ inputMode: 'numeric', pattern: '-?[0-9]*' }} 
                            label="Highest floor" 
                            onChange={handleHighestFloorChange}
                        />
                        <TextField 
                            value={lowestFloor} 
                            inputProps={{ inputMode: 'numeric', pattern: '-?[0-9]*' }} 
                            label="Lowest floor" 
                            onChange={handleLowestFloorChange}
                        />
                    </Field>
                    <Field $border>
                        <FieldLegend>System settings</FieldLegend>
                        <Select
                            value={factory}
                            onChange={handleFactoryChange}
                            disabled={system !== ElevatorSystemVariant.CUSTOM}
                        >
                            <MenuItem value={ElevatorFactoryVariant.DEFAULT}>Default factory</MenuItem>
                            <MenuItem value={ElevatorFactoryVariant.SCAN}>SCAN factory</MenuItem>
                            <MenuItem value={ElevatorFactoryVariant.FCFS}>FCFS factory</MenuItem>
                        </Select>
                        <Select
                            value={strategy}
                            onChange={handleStrategyChange}
                            disabled={system !== ElevatorSystemVariant.CUSTOM}
                        >
                            <MenuItem value={ElevatorPickupStrategy.DEFAULT}>Default strategy</MenuItem>
                            <MenuItem value={ElevatorPickupStrategy.BEST_COST}>Best Cost strategy</MenuItem>
                            <MenuItem value={ElevatorPickupStrategy.BALANCED_BEST_COST}>Balanced Best Cost strategy</MenuItem>
                            <MenuItem value={ElevatorPickupStrategy.RANDOMIZED_BEST_COST}>Randomized Best Cost strategy</MenuItem>
                            <MenuItem value={ElevatorPickupStrategy.LEAST_BUSY}>Least Busy strategy</MenuItem>
                        </Select>
                    </Field>
                </FieldWrapper>
                <FormFooter>
                    <ErrorMessage>
                        {errorMessage}
                    </ErrorMessage>
                    <Button type="submit" variant="contained" disabled={isLoading}>
                        Create    
                    </Button>                
                </FormFooter>
            </Form>
        </Paper>
    )
}


export default SystemForm