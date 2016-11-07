# TOPAddons

![Logo](images/logo.jpg)

A Minecraft mod which adds mod support to The One Probe by McJty

## Currenty supports:

### Forge

- Tank display

### Forestry ([album](http://imgur.com/a/APS3S))

- Error messages for all blocks with GUIs that display them
- Internal tank display
- Breeding progress (no values) and queen display for all beehouses
- Special information displays for Moistener and Multiblock Farms
- Show fruit ripeness on leaves and whether they are pollinated (only when wearing spectacles or apiarist's hat)

### Tinkers' Construct ([album](http://imgur.com/a/F7LbA))

- Extended view of fluids inside the controller when sneaking
- Drying rack progress

### Blood Magic: Alchemical Wizardry ([album](http://imgur.com/a/t9aNg))

- Altar tier and Essence contents (requires divination/seer sigil*)
- Altar crafting progress (requires seer sigil)
- Incense altar tranquility and sacrifice bonus (requires divination/seer sigil)
- Filter configuration for routing nodes (side-sensitive)

### Storage Drawers ([album](http://imgur.com/lXSgOm5))http://imgur.com/a/8jY8H

- More detailed information on stored item stacks
- Stack capacity

### IndustrialCraft 2 ([album](http://imgur.com/a/8jY8H))

- EU bar on most tiles (availability of stored energy varies because of protected/private fields)
- Progress bars on many machine tiles
- Teleporter linked coordinates
- Heat display
- TFBP display
- ...

*Also works in sigil of holding

## Probing Helmets
Craft modded helmets with a probe to get their *probing* variants, works with all non-blacklisted helmets (configurable):
#### These helmets will additionally have a visual "chip" when worn:

- Botania: Manasteel, Elementium, Manaweave and Terrasteel helmets(non-revealing versions)
- Forestry: Apiarist helmet and Spectacles
- Blood Magic: Living and Sentient helmets
- IndustrialCraft 2: Quantum, Nano and Hazmat helmets

![helmets](images/probing_helmets.gif)

Uncraft by putting the helmet back in the crafting grid with nothing else.

## Commands

```/topaddons [option] [value]```
##### This command will modify client-only settings:

- Show the TOP Addons fluid gauge on tiles with internal tanks. (0 to hide) [default: 1] 
- Hide the *vanilla* TOP fluid gauge. (1 to hide) [default: 0]
- Only show Forestry machines' *critical* failure reasons when crouched.(0 for always, 1 for crouching) [default: 0]
- Show IC² machine progress bar in *normal mode*. (0 for crouching only) [default: 1]
- Show note block pitch and instrument. (0 to hide) [default: 1]

```/tophelmet blacklist <add/remove>```
##### Add or remove the currently held helmet to or from the blacklist