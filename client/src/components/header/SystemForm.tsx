import { FormControl, MenuItem, Select, SelectChangeEvent } from "@mui/material"

import styled from "styled-components"
import constants from "../../common/constants"
import { ChangeEvent, useState } from "react"


const Form = styled.form`
    
`

const SystemForm = () => {
    const [system, setSystem] = useState("SCAN")

    const handleSystemChange = (event: SelectChangeEvent<string>) => {
        setSystem(event.target.value)
    }

    return (
        <Form>
            <Select
                value={system}
                onChange={handleSystemChange}
            >
                <MenuItem value={"SCAN"}>SCAN System</MenuItem>
                <MenuItem value={"CUSTOM"}>Custom System</MenuItem>
            </Select>
        </Form>
    )
}

export default SystemForm