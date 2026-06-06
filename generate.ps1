$items = @(
    @{id=15; name="Lucky Die"; cost=21; tier=0; aV=12.0; aS="luck"; rV=2.0; rS="harvesting"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=16; name="Swift Boots"; cost=23; tier=0; aV=25.0; aS="speed"; rV=5.0; rS="health"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=18; name="Golden Sack"; cost=19; tier=0; aV=12.0; aS="harvesting"; rV=5.0; rS="speed"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=20; name="Regeneration Root"; cost=22; tier=0; aV=3.0; aS="hp_regen"; rV=1.0; rS="damage"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=25; name="Explorer's Compass"; cost=25; tier=0; aV=10.0; aS="harvesting"; rV=5.0; rS="health"; aV2=10.0; aS2="luck"; rV2=0.0; rS2=""},

    @{id=14; name="Hunter's Arrow"; cost=45; tier=1; aV=25.0; aS="range"; rV=3.0; rS="damage"; aV2=5.0; aS2="critchance"; rV2=0.0; rS2=""},
    @{id=19; name="Sturdy Shield"; cost=42; tier=1; aV=10.0; aS="blockchance"; rV=5.0; rS="speed"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=23; name="Mage's Scroll"; cost=48; tier=1; aV=12.0; aS="damage"; rV=5.0; rS="health"; aV2=5.0; aS2="luck"; rV2=0.0; rS2=""},
    @{id=26; name="Luckseeker's Token"; cost=50; tier=1; aV=5.0; aS="luck"; rV=2.0; rS="damage"; aV2=15.0; aS2="harvesting"; rV2=0.0; rS2=""},

    @{id=17; name="Vampire Tooth"; cost=85; tier=2; aV=8.0; aS="lifesteal"; rV=4.0; rS="health"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=22; name="Berserker's Axe"; cost=82; tier=2; aV=15.0; aS="damage"; rV=8.0; rS="blockchance"; aV2=5.0; aS2="critchance"; rV2=0.0; rS2=""},
    @{id=21; name="Dragon Scale"; cost=88; tier=2; aV=15.0; aS="blockchance"; rV=10.0; rS="speed"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=24; name="Poison Dart"; cost=90; tier=2; aV=25.0; aS="damage"; rV=10.0; rS="health"; aV2=0.0; aS2=""; rV2=0.0; rS2=""},
    @{id=28; name="Seraph's Healing Amulet"; cost=95; tier=2; aV=50.0; aS="health"; rV=0.0; rS=""; aV2=5.0; aS2="hp_regen"; rV2=0.0; rS2=""},

    @{id=27; name="Cosmic Power Rune"; cost=150; tier=3; aV=30.0; aS="damage"; rV=0.0; rS=""; aV2=10.0; aS2="hp_regen"; rV2=0.0; rS2=""},
    @{id=29; name="Vampire's Lifesteal Pendant"; cost=160; tier=3; aV=12.0; aS="lifesteal"; rV=0.0; rS=""; aV2=100.0; aS2="health"; rV2=0.0; rS2=""}
)

$outDir = "d:\Trash_Clone\Egg_Angry\resources\items\passives"

foreach ($item in $items) {
    # Convert .0 string literal implicitly by formatting it as fixed-point
    $aV  = "{0:0.0}" -f $item.aV
    $rV  = "{0:0.0}" -f $item.rV
    $aV2 = "{0:0.0}" -f $item.aV2
    $rV2 = "{0:0.0}" -f $item.rV2

    $content = @"
[gd_resource type="Resource" script_class="ItemPassive" load_steps=3 format=3]

[ext_resource type="Texture2D" path="res://assets/sprites/Upgrades/$($item.id).png" id="1_icon"]
[ext_resource type="Script" uid="uid://dei327lfm0ybu" path="res://gdj/game/resources/items/ItemPassive.gdj" id="2_script"]

[resource]
script = ExtResource("2_script")
add_value = $aV
add_stat = "$($item.aS)"
add_value2 = $aV2
add_stat2 = "$($item.aS2)"
remove_value = $rV
remove_stat = "$($item.rS)"
remove_value2 = $rV2
remove_stat2 = "$($item.rS2)"
item_name = "$($item.name)"
item_icon = ExtResource("1_icon")
item_tier = $($item.tier)
item_type = 2
item_cost = $($item.cost)
"@

    $fileName = "passive_$($item.id).tres"
    $filePath = Join-Path $outDir $fileName
    $utf8NoBom = New-Object System.Text.UTF8Encoding $false
    [System.IO.File]::WriteAllText($filePath, $content, $utf8NoBom)
    Write-Host "Created $filePath"
}

Write-Host "Done generating tres files."
